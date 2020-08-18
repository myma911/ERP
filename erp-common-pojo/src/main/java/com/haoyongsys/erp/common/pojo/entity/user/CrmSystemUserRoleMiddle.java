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
@TableName("crm_system_user_role_middle")
@ApiModel(value="CrmSystemUserRoleMiddle对象", description="")
public class CrmSystemUserRoleMiddle extends Model<CrmSystemUserRoleMiddle> {

    private static final long serialVersionUID = 1L;

    @TableId("user_role_middle")
    private String userRoleMiddle;

    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;


    @Override
    protected Serializable pkVal() {
        return this.userRoleMiddle;
    }

}
