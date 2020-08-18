package com.haoyongsys.erp.common.pojo.mongodb;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 共享文本实体类
 */
@Data
public class SharedinfoText implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String _id;
	
	
	@ApiModelProperty(value = "主键")
	private String sharedinfoTextId;

	@ApiModelProperty(value = "crm_sharedinfo_set_id 表")
	private String sharedinfoSetId;

	@ApiModelProperty(value = "商户ID")
	private String businessId;

	@ApiModelProperty(value = "文档标题")
	private String title;

	@ApiModelProperty(value = "文档内容")
	private String content;

	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "创建人  用户表user_id")
	private String createUserId;

	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "修改人 user_id")
	private String updateUserId;

	@ApiModelProperty(value = "状态 Y正常 N已删除")
	private String usingFlag;

	@ApiModelProperty(value = "可见岗位id(英文逗号分隔)")
	private String visibleRoleId;
	private List<String> visibleRoleIdList;

	@ApiModelProperty(value = "可见岗位名称(英文逗号分隔)")
	private String visibleRoleName;
	private List<String> visibleRoleNameList;

}
