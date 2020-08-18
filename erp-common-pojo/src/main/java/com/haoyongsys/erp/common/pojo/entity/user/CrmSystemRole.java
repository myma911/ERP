package com.haoyongsys.erp.common.pojo.entity.user;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("crm_system_role")
@ApiModel(value="CrmSystemRole对象", description="")
public class CrmSystemRole extends Model<CrmSystemRole> {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "备注")
    @TableField("role_remark")
    private String roleRemark;

    @ApiModelProperty(value = "部门id")
    @TableField("department_id")
    private String departmentId;

    @TableField("business_id")
    private String businessId;

    @ApiModelProperty(value = "是否删除")
    @TableField("using_flag")
    private String usingFlag;

    @ApiModelProperty(value = "角色类别(0管理员,1其他)")
    @TableField("role_type")
    private String roleType;

    @TableField("role_sort")
    private BigDecimal roleSort;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
