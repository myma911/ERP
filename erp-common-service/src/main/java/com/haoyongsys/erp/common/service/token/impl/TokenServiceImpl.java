package com.haoyongsys.erp.common.service.token.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haoyongsys.erp.common.base.jwt.JwtUtil;
import com.haoyongsys.erp.common.dao.mapper.token.TokenMapper;
import com.haoyongsys.erp.common.pojo.entity.token.TokenEntity;
import com.haoyongsys.erp.common.service.token.TokenService;

import io.jsonwebtoken.Claims;



@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, TokenEntity> implements TokenService {
	
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@Override
	public TokenEntity queryByToken(String token) {
		return this.getOne(new QueryWrapper<TokenEntity>().eq("token", token));
	}

	@Override
	public TokenEntity createToken(String userId) {

		long nowMillis = System.currentTimeMillis();
		//生成token
		String token = jwtUtil.createJWT(nowMillis, userId, "");
		Claims claims = jwtUtil.parseJWT(token);
		Date expiration = claims.getExpiration();
		
		//保存或更新用户token
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		tokenEntity.setUpdateTime(new Date(nowMillis));
		tokenEntity.setExpireTime(expiration);
		this.saveOrUpdate(tokenEntity);

		return tokenEntity;
	}

	@Override
	public boolean expireToken(String userId){
		Date now = new Date();
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setUpdateTime(now);
		tokenEntity.setExpireTime(now);
		return this.saveOrUpdate(tokenEntity);
	}
}
