package com.haoyongsys.erp.api.controller;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.haoyongsys.erp.common.dao.elasticsearch.CustomerDao;
import com.haoyongsys.erp.common.pojo.elasticsearch.Customer;
import com.haoyongsys.erp.common.pojo.entity.customer.CrmCustomers;
import com.haoyongsys.erp.common.service.customer.ICrmCustomersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Snippet {
	
	
	@Autowired
	private ICrmCustomersService customersService;

	@MockBean
	private CustomerDao customersDao;

	@Test
	public void getUserById() throws Exception {
		// 定义当调用mock userDao的getUserById()方法，并且参数为3时，就返回id为200、name为I'm mock3的user对象
		Customer customers = new Customer();
		customers.setId("3");
		
		Optional<Customer> optional = Optional.of(customers);
		
		Mockito.when(customersDao.findById("3")).thenReturn(optional);


		CrmCustomers cus = customersService.getById("3");

		
		Assert.assertNotNull(cus);
		Assert.assertEquals(cus.getCustomerId(), "3");
	}
}
