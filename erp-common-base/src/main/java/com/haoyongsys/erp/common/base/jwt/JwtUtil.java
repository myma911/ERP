package com.haoyongsys.erp.common.base.jwt;

import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 */
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtil {
    private String key ;

    private long ttl ;

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public String createJWT(long nowMillis, String id, String subject) {
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
            .setSubject(subject)
            .setIssuedAt(now)
            .signWith(SignatureAlgorithm.HS256, key);
            //.claim("roles", roles);   // 自定义信息
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return  Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(jwtStr)
            .getBody();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
