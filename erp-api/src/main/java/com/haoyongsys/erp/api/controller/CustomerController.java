package com.haoyongsys.erp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoyongsys.erp.common.pojo.PageResult;
import com.haoyongsys.erp.common.pojo.R2;
import com.haoyongsys.erp.common.pojo.elasticsearch.Customer;
import com.haoyongsys.erp.common.pojo.entity.customer.CrmCustomers;
import com.haoyongsys.erp.common.service.customer.ICrmCustomersService;

import cn.aaron911.idempotent.annotation.ApiIdempotent;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/customer")
@Api(tags = "客户")
public class CustomerController {

	@Autowired
	private ICrmCustomersService customersService;

	@GetMapping(value = "/getAllCustomer")
	@ApiOperation("获取所有客户")
	public R2 getAllCustomer() {
		List<CrmCustomers> list = customersService.list();
		return R2.OK(list);
	}
	
	
	
	
	
	
    /**
     * 添加客户
     * 
     */
	@ApiOperation("添加客户")
	@PostMapping(value = "/add")
    public R2 add(@RequestBody Customer customer) {
		
		List<Customer> list = new ArrayList<Customer>();
		
		for(int i = 40010; i < 1000000; i++) {
			System.out.println(i);
			Customer c = new Customer();
			BeanUtil.copyProperties(customer, c);
			c.setId(i + "");
			list.add(c);
			if (i%500 == 0) {
				customersService.saveAll(list);
				list.clear();
			}
		}
        return R2.OK();
    }
	
	
	
	
    /**
     * 分页查询客户
     * @param keywords
     * @param page
     * @param size
     * @return
     */
	@ApiOperation("查询客户")
	@GetMapping(value = "/search/{keywords}/{page}/{size}")
    public R2 findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<Customer> pageResult = customersService.findByTitleLike(keywords, page, size);
        return R2.OK(new PageResult<Customer>(pageResult.getTotalElements(), pageResult.getContent()));
    }
	
	

    /**
     * 分页查询客户
     * @param keywords
     * @param page
     * @param size
     * @return
     */
	@ApiOperation("查询客户2")
	@GetMapping(value = "/search2/{keywords}/{page}/{size}")
    public R2 search2(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<Customer> pageResult = customersService.search(keywords, page, size);
        return R2.OK(new PageResult<Customer>(pageResult.getTotalElements(), pageResult.getContent()));
        
    }
	

}
