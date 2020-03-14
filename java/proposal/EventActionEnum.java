package proposal;

public enum EventActionEnum {
	CREATED("created"),
	ADDED("added"),
	UPDATED("updated"),
	DELETED("deleted"),
	REMOVED("removed");

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
