package com.haoyongsys.erp.api.controller;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.haoyongsys.erp.common.pojo.elasticsearch.Customer;
import com.haoyongsys.erp.common.service.customer.ICrmCustomersService;

//指用SpringRunner来运行，SpringRunner is an alias for the SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
//@SpringBootTest是SpringBoot的一个用于测试的注解，通过SpringApplication在测试中创建ApplicationContext
@SpringBootTest
//@AutoConfigureMockMvc是用于自动配置MockMvc
@AutoConfigureMockMvc
public class MockCustomerControllerTest {

	// 配置MockMvc
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private ICrmCustomersService customersService;

	@Test
	public void TestXXX() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/search2/公司/1/10")
				.contentType(MediaType.APPLICATION_JSON).param("xxx", "xxx")

		).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

	}

	@Test
	public void testGetSequence() {

		Page<Customer> search = customersService.search("公司", 1, 10);
		System.out.println(search);
	}

	@Test
	@Rollback(true)
	public void putUserMsg() throws Exception {
		this.mockMvc
				.perform((MockMvcRequestBuilders.put("/user/userMsg/003"))
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("userName", "新名字03号")
						.session((MockHttpSession) getLoginSession())
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andDo(MockMvcResultHandlers.print()); // print
	}

	/**
	 * 获取登入信息session
	 * 
	 * @return
	 * @throws Exception
	 */
	private HttpSession getLoginSession() throws Exception {

		MvcResult result = this.mockMvc.perform((MockMvcRequestBuilders.get("/user/userMsg/loginUser/loginUser")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		return result.getRequest().getSession();
	}
}