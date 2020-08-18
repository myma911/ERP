package com.haoyongsys.erp.common.pojo.entity.sharedinfo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 共享信息-文本类业务数据表
 * </p>
 *
 * @author Aaron
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("crm_sharedinfo_text")
@ApiModel(value="CrmSharedinfoText对象", description="共享信息-文本类业务数据表")
public class CrmSharedinfoText extends Model<CrmSharedinfoText> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("sharedinfo_text_id")
    private String sharedinfoTextId;

    @ApiModelProperty(value = "crm_sharedinfo_set_id 表")
    @TableField("sharedinfo_set_id")
    private String sharedinfoSetId;

    @ApiModelProperty(value = "商户ID")
    @TableField("business_id")
    private String businessId;

    @ApiModelProperty(value = "文档标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "文档内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人  用户表user_id")
    @TableField("create_user_id")
    private String createUserId;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人 user_id")
    @TableField("update_user_id")
    private String updateUserId;

    @ApiModelProperty(value = "状态 Y正常 N已删除")
    @TableField("using_flag")
    private String usingFlag;

    @ApiModelProperty(value = "可见岗位id(英文逗号分隔)")
    @TableField("visible_role_id")
    private String visibleRoleId;

    @ApiModelProperty(value = "可见岗位名称(英文逗号分隔)")
    @TableField("visible_role_name")
    private String visibleRoleName;


    @Override
    protected Serializable pkVal() {
        return this.sharedinfoTextId;
    }

}
