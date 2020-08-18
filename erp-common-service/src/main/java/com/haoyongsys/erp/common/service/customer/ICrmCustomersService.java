package com.haoyongsys.erp.common.service.customer;

import org.springframework.data.domain.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haoyongsys.erp.common.pojo.elasticsearch.Customer;
import com.haoyongsys.erp.common.pojo.entity.customer.CrmCustomers;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aaron
 * @since 2020-08-10
 */
public interface ICrmCustomersService extends IService<CrmCustomers> {
	
	void add(Customer customer);

	void saveAll(Iterable<Customer> entities);
	
	Page<Customer> findByTitleLike(String keywords, int page, int size);

	Page<Customer> search(String keywords, int page, int size);

}
