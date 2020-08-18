package com.haoyongsys.erp.common.service.customer.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haoyongsys.erp.common.dao.elasticsearch.CustomerDao;
import com.haoyongsys.erp.common.dao.mapper.customer.CrmCustomersMapper;
import com.haoyongsys.erp.common.pojo.elasticsearch.Customer;
import com.haoyongsys.erp.common.pojo.entity.customer.CrmCustomers;
import com.haoyongsys.erp.common.service.customer.ICrmCustomersService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2020-08-10
 */
@Service
public class CrmCustomersServiceImpl extends ServiceImpl<CrmCustomersMapper, CrmCustomers>
		implements ICrmCustomersService {

	@Autowired
	private CustomerDao customersDao;
	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	/**
	 * 增加
	 * 
	 * @param
	 */
	@Override
	public void add(Customer customer) {
		customersDao.save(customer);
	}

	@Override
	public void saveAll(Iterable<Customer> entities) {
		customersDao.saveAll(entities);
	}

	@Override
	public Page<Customer> findByTitleLike(String keywords, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return customersDao.findByTitleOrContentLike(keywords, keywords, pageRequest);
	}

	@Override
	public Page<Customer> search(String keywords, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		// QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("*"+keywords+"*",
		// "title", "content", "customerTypeId");

		WildcardQueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("title", "*" + keywords + "*");// 搜索名字中含有jack的文档
		WildcardQueryBuilder queryBuilder2 = QueryBuilders.wildcardQuery("content", "*" + keywords + "*");// 搜索interest中含有read的文档

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// name中含有jack或者interest含有read，相当于or
		boolQueryBuilder.should(queryBuilder1);
		boolQueryBuilder.should(queryBuilder2);
		
		return customersDao.search(boolQueryBuilder, pageRequest);
	}

}
