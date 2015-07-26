package com.yingzi.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yingzi.admin.enums.PowerCheckEnum;
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface PowerCheck {
	public PowerCheckEnum type() default PowerCheckEnum.LOGIN;
}
