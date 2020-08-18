package com.haoyongsys.erp.common.dao.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.haoyongsys.erp.common.pojo.elasticsearch.Customer;

/**
 * 客户数据访问层接口
 */
public interface CustomerDao extends ElasticsearchRepository<Customer, String> {
	
	/**
	 * 检索 
	 * @param
	 * @return      
	 */
	public Page<Customer> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
