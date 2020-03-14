package proposal;

public enum EventActionEnum {
	CREATE("create"),
	UPDATE("update"),
	DELETE("delete");

	private String code;
	
	private EventActionEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static EventActionEnum getByCode(String code) {
		for (EventActionEnum action : EventActionEnum.values()) {
			if (action.code.equals(code)) {
				return action;
			}
		}
		
		return null;
	}

}
