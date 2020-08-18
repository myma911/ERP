package com.haoyongsys.erp.common.pojo.entity.user;

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
@TableName("crm_system_authority_role_middle")
@ApiModel(value="CrmSystemAuthorityRoleMiddle对象", description="")
public class CrmSystemAuthorityRoleMiddle extends Model<CrmSystemAuthorityRoleMiddle> {

    private static final long serialVersionUID = 1L;

    @TableId("authority_role_middle")
    private String authorityRoleMiddle;

    @ApiModelProperty(value = "权限id")
    @TableField("authority_id")
    private String authorityId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private String roleId;

    @TableField("business_id")
    private String businessId;


    @Override
    protected Serializable pkVal() {
        return this.authorityRoleMiddle;
    }

}
