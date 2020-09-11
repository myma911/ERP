package com.haoyongsys.erp.common.service.user.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haoyongsys.erp.common.dao.mapper.user.ARightUserMapper;
import com.haoyongsys.erp.common.pojo.entity.user.ARightUser;
import com.haoyongsys.erp.common.service.user.IARightUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2020-08-10
 */
@Service
@Slf4j
public class ARightUserServiceImpl extends ServiceImpl<ARightUserMapper, ARightUser> implements IARightUserService {

	
	@Override
	public ARightUser test() {
		log.info("test");
		return new ARightUser();
	}

}
