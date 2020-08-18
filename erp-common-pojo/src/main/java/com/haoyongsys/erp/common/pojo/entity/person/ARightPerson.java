package com.haoyongsys.erp.common.pojo.entity.person;

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
@ApiModel(value="ARightPerson对象", description="")
@TableName("a_right_person")
public class ARightPerson extends Model<ARightPerson> {

    private static final long serialVersionUID = 1L;

    @TableId("person_id")
    private String personId;

    @ApiModelProperty(value = "姓名")
    private String personName;

    @ApiModelProperty(value = "性别(Y男,N女)")
    private String sex;

    @ApiModelProperty(value = "头像")
    private String pic;

    @ApiModelProperty(value = "手机号")
    private String mobiletelephone;

    @ApiModelProperty(value = "部门id")
    private String depId;

    @ApiModelProperty(value = "离职标识(Y/N)?")
    private String dimissionFlag;

    @ApiModelProperty(value = "排序号")
    private BigDecimal personSort;

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

    @ApiModelProperty(value = "是否统计工作简报(Y统计N不统计)")
    private String isStatisticsWorkBriefing;


    @Override
    protected Serializable pkVal() {
        return this.personId;
    }

}
