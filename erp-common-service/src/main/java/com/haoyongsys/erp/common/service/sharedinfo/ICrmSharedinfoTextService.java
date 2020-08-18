package com.haoyongsys.erp.common.service.sharedinfo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haoyongsys.erp.common.pojo.entity.sharedinfo.CrmSharedinfoText;
import com.haoyongsys.erp.common.pojo.mongodb.SharedinfoText;

/**
 * <p>
 * 共享信息-文本类业务数据表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2020-08-11
 */
public interface ICrmSharedinfoTextService extends IService<CrmSharedinfoText> {

	List<SharedinfoText> findAll();

	SharedinfoText findById(String id);

	void add(SharedinfoText sharedinfoText);

	void saveAll(Iterable<SharedinfoText> iterable);

	Page<SharedinfoText> findByTitleOrContentLike(String keyword, int page, int size);

}
