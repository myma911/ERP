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
@TableName("crm_system_authority")
@ApiModel(value="CrmSystemAuthority对象", description="")
public class CrmSystemAuthority extends Model<CrmSystemAuthority> {

    private static final long serialVersionUID = 1L;

    @TableId("authority_id")
    private String authorityId;

    @ApiModelProperty(value = "权限名称")
    @TableField("authority_name")
    private String authorityName;

    @ApiModelProperty(value = "所属模块名称")
    @TableField("modular_name")
    private String modularName;

    @TableField("order_num")
    private BigDecimal orderNum;

    @ApiModelProperty(value = "提示语")
    @TableField("cue_words")
    private String cueWords;

    @ApiModelProperty(value = "二级排序  具体权限点排序")
    @TableField("order_num_two")
    private BigDecimal orderNumTwo;


    @Override
    protected Serializable pkVal() {
        return this.authorityId;
    }

}
