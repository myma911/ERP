package com.haoyongsys.erp.common.pojo.entity.customer;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
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
@ApiModel(value="CrmCustomers对象", description="")
@TableName("crm_customers")
public class CrmCustomers extends Model<CrmCustomers> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户代码")
    @TableId("customer_id")
    private String customerId;

    @ApiModelProperty(value = "客户类型代码")
    private String customerTypeId;

    @ApiModelProperty(value = "来源代码")
    private String lyId;

    @ApiModelProperty(value = "客户状态代码")
    private String customerStateId;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "客户电话")
    private String customerTel;

    @ApiModelProperty(value = "施工小区")
    private String constructionDistrict;

    @ApiModelProperty(value = "客户地址")
    private String customerAdd;

    @ApiModelProperty(value = "电子邮箱")
    private String customerEmail;

    @ApiModelProperty(value = "建档人uid")
    private String insertUserId;

    @ApiModelProperty(value = "建档人pid")
    private String insertPersonId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "当前联系人代码")
    private String currentContactId;

    @ApiModelProperty(value = "当前联系人姓名")
    private String currentContactName;

    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechar;

    @ApiModelProperty(value = "接待人员代码(多个逗号分隔)")
    private String receptionistId;

    @ApiModelProperty(value = "接待人员姓名(多个逗号分隔)")
    private String receptionistName;

    @ApiModelProperty(value = "项目工程师代码(多个逗号分隔)")
    private String programEngineerId;

    @ApiModelProperty(value = "项目工程师姓名(多个逗号分隔)")
    private String programEngineerName;

    @ApiModelProperty(value = "技术工程师代码(多个逗号分隔)")
    private String technicEngineerId;

    @ApiModelProperty(value = "技术工程师姓名(多个逗号分隔)")
    private String technicEngineerName;

    @ApiModelProperty(value = "项目介绍人")
    private String customerIntroducer;

    @ApiModelProperty(value = "介绍人所在单位")
    private String customerIntroducerCompany;

    @ApiModelProperty(value = "介绍人联系方式")
    private String customerIntroducerContact;

    @ApiModelProperty(value = "业主方设计师")
    private String customerDesigner;

    @ApiModelProperty(value = "业主方设计师所在单位")
    private String customerDesignerCompany;

    @ApiModelProperty(value = "业主方设计师联系方式")
    private String customerDesignerContact;

    @ApiModelProperty(value = "是否删除")
    private String usingFlag;

    private String businessId;

    private String updateUserId;

    private String updatePersonId;

    @ApiModelProperty(value = "签约日期")
    private LocalDate contractDate;

    @ApiModelProperty(value = "接待时间")
    private LocalDate receptDate;

    @ApiModelProperty(value = "客户负责人 ")
    private String customerContacts;

    @ApiModelProperty(value = "客户电话2")
    private String customerTel2;

    @ApiModelProperty(value = "客户负责人电话")
    private String customerContactsTel;

    @ApiModelProperty(value = "客户级别")
    private String customerLevelId;

    @ApiModelProperty(value = "2019.12.3 添加客户方案更新时间")
    private LocalDateTime customerProgramUpdateTime;

    @ApiModelProperty(value = "隐藏客户：用户勾选此选项后，除建档人、接待人、有“查看他人客户信息”权限的用户外，其他用户的列表中不显示此客户的信息。")
    private Integer hideCustomer;

    @ApiModelProperty(value = "隐藏电话")
    private Integer hideCustomerTel;

    @ApiModelProperty(value = "隐藏客户维护记录")
    private Integer hideCustomerMaintenanceRecord;

    @ApiModelProperty(value = "客户的建档人或接待人对客户进行修改一次客户基本信息或提交一次服务记录或发起一个流程视为一次维护，维护后维护周期将从维护日期重新开始计算，直至客户状态由潜在改变为成交、完工或闲置。")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "用户的潜在客户被放入公海前一天的提示方式：给此客户的建档人、接待人(重复的人员只发一次)发送通知消息，内容为：您的客户“姓名/小区/地址/手机号”已有XX天未进行维护，请及时跟进，超过XX天未跟进将会放入公海客户中。")
    private Integer beforePublicSendMessage;

    @ApiModelProperty(value = "放入公海后的提示：给此客户的建档人、接待人(重复的人员只发一次)发送通知消息，内容为您的客户“姓名/小区/地址/手机号”超过XX天未跟进，已放入公海客户中。生成客户服务记录：时间为放入公海的时间，人员为设置公海规则的人员姓名，内容为“超过XX天未跟进，已放入公海客户中”。")
    private Integer afterPublicSendMessage;


    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }

}
