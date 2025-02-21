package model;

public enum EventSchemaEnum {
	PROPOSAL("proposal"),
	WARRANTY("warranty"),
	PROPONENT("proponent");

	private String code;
	
	private EventSchemaEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static EventSchemaEnum getByCode(String code) {
		if (code.isBlank()) {
			return null;
		}
		
		for (EventSchemaEnum schema : EventSchemaEnum.values()) {
			if (schema.code.equals(code)) {
				return schema;
			}
		}
		
		return null;
	}

}
