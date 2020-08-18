package com.haoyongsys.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import cn.aaron911.buron.annotation.EnableBuronConfiguration;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

@SpringBootApplication
@EnableCaching
@EnableBuronConfiguration
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public Snowflake idWorker() {
		return IdUtil.getSnowflake(1, 1);
	}

}
