package com.ytd.mds.config.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DataSource {
	
	String value() default "";	//注解时指定，不可变更
	
	boolean manual() default false;	//手动指定，通过方法参数改动
	
}
