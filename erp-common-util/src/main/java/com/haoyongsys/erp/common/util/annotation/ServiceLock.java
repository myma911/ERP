package com.haoyongsys.erp.common.util.annotation;
import java.lang.annotation.*; 
/**
 * 分布式 同步锁
 * 
 * description 描述在redis中要加锁的关键字，默认使用"ServiceLock"
 * 
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface ServiceLock { 
	 String description()  default "ServiceLock";
}
