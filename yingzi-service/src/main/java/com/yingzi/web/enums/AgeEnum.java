package com.yingzi.web.enums;

public enum AgeEnum
{
	ONE(1, 0, 30, "30岁以下"), TWO(2, 31, 50, "31-50岁"), THREE(3, 51, 120, "51岁以上");
	private Integer value;
	private Integer minAge;
	private Integer maxAge;
	private String desc;

	private AgeEnum(Integer value, Integer minAge, Integer maxAge, String desc)
	{
		this.value = value;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.desc = desc;
	}

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}

	public Integer getMinAge()
	{
		return minAge;
	}

	public void setMinAge(Integer minAge)
	{
		this.minAge = minAge;
	}

	public Integer getMaxAge()
	{
		return maxAge;
	}

	public void setMaxAge(Integer maxAge)
	{
		this.maxAge = maxAge;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public static AgeEnum toEnum(int code)
	{
		for (AgeEnum ageEnum : AgeEnum.values())
		{
			if (code == ageEnum.getValue())
			{
				return ageEnum;
			}
		}
		return null;
	}
}
