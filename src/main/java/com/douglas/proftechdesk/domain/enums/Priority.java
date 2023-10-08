package com.douglas.proftechdesk.domain.enums;

public enum Priority {

	LOW(0, "LOW"), 
	MEDIUM(1, "MEDIUM"), 
	HIGH(2, "HIGH");

	private Integer code;
	private String desc;

	private Priority(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static Priority toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (Priority condition : Priority.values()) {
			if (code.equals(condition.getCode())) {
				return condition;
			}
		}

		throw new IllegalArgumentException("Invalid Priority");
	}
}
