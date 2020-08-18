package com.haoyongsys.erp.common.service.token;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haoyongsys.erp.common.pojo.entity.token.TokenEntity;


/**
 * 用户Token
 *
 */
public interface TokenService extends IService<TokenEntity> {

	TokenEntity queryByToken(String token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token信息
	 */
	TokenEntity createToken(String userId);

	/**
	 * 设置token过期
	 * @param userId 用户ID
	 * @return 
	 */
	boolean expireToken(String userId);

}
