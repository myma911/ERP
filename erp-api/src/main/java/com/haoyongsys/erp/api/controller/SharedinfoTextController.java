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
import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.entity.sharedinfo.CrmSharedinfoText;
import com.haoyongsys.erp.common.pojo.mongodb.SharedinfoText;
import com.haoyongsys.erp.common.service.sharedinfo.ICrmSharedinfoTextService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sharedinfo")
@Api(tags = "共享文本")
public class SharedinfoTextController {

	@Autowired
	private ICrmSharedinfoTextService sharedinfoTextService;
	
	@Autowired
	private Snowflake snowflake;

	@GetMapping(value = "/getAll")
	@ApiOperation("获取所有")
	public R getAll() {
		List<SharedinfoText> findAll = sharedinfoTextService.findAll();
		return R.OK(findAll);
	}
	
	
    /**
     * 添加
     * 
     */
	@ApiOperation("添加")
	@PostMapping(value = "/add")
    public R add(
		@RequestBody SharedinfoText sharedinfoText
		,String businessId
	) {
		
//		List<SharedinfoText> list = new ArrayList<SharedinfoText>();
//		
//		List<CrmSharedinfoText> list2 = sharedinfoTextService.list();
//		list2.forEach(crmSharedinfoText->{
//			for(int i = 1; i < 1000; i++) {
//				System.out.println(i);
//				SharedinfoText s = new SharedinfoText();
//				BeanUtil.copyProperties(crmSharedinfoText, s);
//				String nextIdStr = snowflake.nextIdStr();
//				s.set_id(nextIdStr);
//				s.setSharedinfoTextId(nextIdStr);
//				list.add(s);
//				if (i%500 == 0) {
//					sharedinfoTextService.saveAll(list);
//					list.clear();
//				}
//			}
//		});
		
		sharedinfoTextService.add(sharedinfoText);
        return R.OK();
    }
	
	

    /**
     * 分页查询
     * @param keywords
     * @param page
     * @param size
     * @return
     */
	@ApiOperation("查询")
	@GetMapping(value = "/search/{keywords}/{page}/{size}")
    public R search(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<SharedinfoText> pageResult = sharedinfoTextService.findByTitleOrContentLike(keywords, page, size);
        return new R(StateCodeEnum.OK.getCode(), "查询成功", new PageResult<SharedinfoText>(pageResult.getTotalElements(), pageResult.getContent()));
    }
	
	

}
