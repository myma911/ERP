package com.haoyongsys.erp.common.pojo.entity.user;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ARightUser对象", description="")
@TableName("a_right_user")
public class ARightUser extends Model<ARightUser> {

    private static final long serialVersionUID = 1L;


    @TableId("user_id")
    private String userId;

    @ApiModelProperty(value = "人员id")
    private String personId;

    @ApiModelProperty(value = "账号密码")
    private String userPassword;

    @ApiModelProperty(value = "批示意见密码")
    private String processPassword;

    @ApiModelProperty(value = "排序号")
    private BigDecimal userSort;

    @ApiModelProperty(value = "商户id")
    private String businessId;

    @ApiModelProperty(value = "是否使用(Y/N)")
    private String usingFlag;

    @ApiModelProperty(value = "创建账号id")
    private String insertUserId;

    @ApiModelProperty(value = "创建人id")
    private String insertPersonId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "极光设备id")
    private String jpushRegistrationId;

    @ApiModelProperty(value = "是否不可以被删除")
    private String isnotdelete;

    @ApiModelProperty(value = "是否需要引导")
    private String isguide;

    @ApiModelProperty(value = "是否需要修改密码（Y：是，N：否）")
    private String isneed;

    @ApiModelProperty(value = "2020.3.1 注册的手机号")
    private String userIid;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
