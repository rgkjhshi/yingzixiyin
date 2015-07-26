package com.yingzi.admin.enums;

public enum PowerCheckEnum {
	LOGIN(1,"登陆枚举","登陆进行检查的类型"),
	POWER(2,"权限枚举","权限进行检查的类型");
	public Integer  id;
	public String name;
	public String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private PowerCheckEnum(Integer id, String name, String desc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
	}
	public static PowerCheckEnum toEnum(int id){
		for(PowerCheckEnum pe:PowerCheckEnum.values()){
			if(id==pe.getId()){
				return pe;
			}
		}
		return null;
	}
	
}
