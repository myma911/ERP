package com.haoyongsys.erp.common.service.sharedinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haoyongsys.erp.common.dao.mapper.sharedinfo.CrmSharedinfoTextMapper;
import com.haoyongsys.erp.common.dao.mongodb.SharedinfoTextDao;
import com.haoyongsys.erp.common.pojo.entity.sharedinfo.CrmSharedinfoText;
import com.haoyongsys.erp.common.pojo.mongodb.SharedinfoText;
import com.haoyongsys.erp.common.service.sharedinfo.ICrmSharedinfoTextService;


/**
 * <p>
 * 共享信息-文本类业务数据表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2020-08-11
 */
@Service
public class CrmSharedinfoTextServiceImpl extends ServiceImpl<CrmSharedinfoTextMapper, CrmSharedinfoText> implements ICrmSharedinfoTextService {

	@Autowired
    private SharedinfoTextDao sharedinfoTextDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查看全部记录
     * @return
     */
    @Override
    public List<SharedinfoText> findAll(){
        return sharedinfoTextDao.findAll();
    }

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    @Override
    public SharedinfoText findById(String id){
        return sharedinfoTextDao.findById(id).get();
    }

    /**
     * 增加
     * @param spit
     */
    @Override
    @CacheEvict(value = "SharedinfoText", allEntries=true)
    public void add(SharedinfoText sharedinfoText) {
        sharedinfoTextDao.save(sharedinfoText);
    }
    
    @Override
    @CacheEvict(value = "SharedinfoText", allEntries=true)
    public void saveAll(Iterable<SharedinfoText> iterable) {
        sharedinfoTextDao.saveAll(iterable);
    }
    
    

    /**
     * 修改
     * @param spit
     */
    @CacheEvict(value = "SharedinfoText", allEntries=true)
    public void update(SharedinfoText spit){
    	sharedinfoTextDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    @CacheEvict(value = "SharedinfoText", allEntries=true)
    public void deleteById(String id){
    	sharedinfoTextDao.deleteById(id);
    }

    /**
     * 根据title或者content获取内容
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @Override
    @Cacheable(value = "SharedinfoText" ,key = "targetClass + methodName + #p0 + #p1 + #p2")
    public Page<SharedinfoText> findByTitleOrContentLike(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return sharedinfoTextDao.findByTitleOrContentLike(keyword, keyword, pageRequest);
    }

    /**
     * 点赞
     * @param id
     */
//    public void updatethumbup(String id){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is(id));
//        //封装修改的数据内容
//        Update update = new Update();
//        update.inc("thumbup", 1);
//        mongoTemplate.updateFirst(query, update, "share");
//    }

}
