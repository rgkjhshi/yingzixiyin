package com.yingzi.web.enums;

public enum AgeEnum {
	ONE(1,0,30,"30岁以下"),
	TWO(2,31,50,"31-50岁"),
	THREE(2,51,120,"51岁以上");
	private Integer key;
	private Integer minAge;
	private Integer maxAge;
	private String desc;
	
	private AgeEnum(Integer key, Integer minAge, Integer maxAge, String desc) {
		this.key = key;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.desc = desc;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static AgeEnum toEnum(int key){
		for(AgeEnum ageEnum:AgeEnum.values()){
			if(key==ageEnum.getKey()){
				return ageEnum;
			}
		}
		return null;
	}
}
