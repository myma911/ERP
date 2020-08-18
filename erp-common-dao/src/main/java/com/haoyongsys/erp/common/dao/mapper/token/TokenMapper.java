
package com.haoyongsys.erp.common.dao.mapper.token;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haoyongsys.erp.common.pojo.entity.token.TokenEntity;

/**
 * 用户Token
 *
 */
@Mapper
public interface TokenMapper extends BaseMapper<TokenEntity> {
	
}
