package com.haoyongsys.erp.common.dao.mongodb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.haoyongsys.erp.common.pojo.mongodb.SharedinfoText;

/**
 * 共享文本数据访问层
 */
public interface SharedinfoTextDao extends MongoRepository<SharedinfoText, String> {

    /**
     *  根据title或者content获取内容
     * @param parentid
     * @param pageable
     * @return
     */
    public Page<SharedinfoText> findByTitleOrContentLike(String title, String Content, Pageable pageable);
}
