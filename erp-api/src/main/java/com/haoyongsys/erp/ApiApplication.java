package com.haoyongsys.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cn.aaron911.buron.annotation.EnableBuronConfiguration;
import cn.aaron911.encrypt.api.annotation.EnableSecurity;
import cn.aaron911.idempotent.annotation.EnableIdempotent;
import cn.aaron911.lock.redisson.annotation.EnableRedissonLock;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

@SpringBootApplication
@EnableBuronConfiguration
@EnableSecurity
@EnableRedissonLock
@EnableIdempotent
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public Snowflake idWorker() {
		return IdUtil.getSnowflake(1, 1);
	}
	
	
	
	
	
	
	

}
