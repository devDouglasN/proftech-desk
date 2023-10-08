package com.douglas.proftechdesk.domain.enums;

public enum Profile {

	ADMIN(0, "ROLE_ADMIN"), 
	CUSTOMER(1, "ROLE_CUSTOMER"), 
	TECHNICAL (2, "ROLE_TECHNICAL ");

	private Integer code;
	private String desc;

	private Profile(Integer code, String desc) {
	    this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static Profile toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (Profile condition : Profile.values()) {
			if (code.equals(condition.getCode())) {
				return condition;
			}
		}

		throw new IllegalArgumentException("Invalid Profile");
	}
}
