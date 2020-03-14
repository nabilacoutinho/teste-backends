package model;

public enum EventSchemaEnum {
	PROPOSAL("proposal"),
	WARRANTY("warranty"),
	PROPOSENT("proposent");

	private String code;
	
	private EventSchemaEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static EventSchemaEnum getByCode(String code) {
		for (EventSchemaEnum schema : EventSchemaEnum.values()) {
			if (schema.code.equals(code)) {
				return schema;
			}
		}
		
		return null;
	}

}
