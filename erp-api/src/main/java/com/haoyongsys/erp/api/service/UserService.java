package com.haoyongsys.erp.api.service;

import org.springframework.stereotype.Service;

import cn.aaron911.lock.redisson.annotation.RedissonLock;

@Service
public class UserService {
	
	
	
	@RedissonLock
	public String xx() {
		System.out.println("xx");
		
		return "xx";
	}

}
