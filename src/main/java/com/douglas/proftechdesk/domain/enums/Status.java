package com.douglas.proftechdesk.domain.enums;

public enum Status {

	OPENED(0, "OPENED"), 
	PROGRESS(1, "PROGRESS"), 
	CLOSED(2, "CLOSED");

	private Integer code;
	private String desc;

	private Status(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static Status toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (Status condition : Status.values()) {
			if (code.equals(condition.getCode())) {
				return condition;
			}
		}

		throw new IllegalArgumentException("Invalid Status");
	}
}
