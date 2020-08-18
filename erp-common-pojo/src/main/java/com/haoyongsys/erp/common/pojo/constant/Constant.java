package com.haoyongsys.erp.common.pojo.constant;

public class Constant {
	
	
	
	/**
	 * 接口幂等性redis参数
	 * @ClassName: IdempotentComponentRedis
	 * @author Aaron
	 * @date 2020年8月12日 下午3:43:36
	 */
	public static class IdempotentComponent{
		public static final String TOKEN_PREFIX = "Unique_Identification_";
		public static final long EXPIRE_TIME_SECOND = 60 * 60 * 24;
		public static final String IDEMPOTENT_NAME = "unique";
	}


}
