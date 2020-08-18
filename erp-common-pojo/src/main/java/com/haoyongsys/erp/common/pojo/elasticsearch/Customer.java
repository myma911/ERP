package com.haoyongsys.erp.common.pojo.elasticsearch;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章实体类
 * indexName相当于mysql的database
 * type相当于mysql的table, 4.0以后已经过时了
 * IK提供了两种分词算法：ik_smart和ik_max_word
 * 其中ik_smart为最少切分,ik_max_word为最细粒度划分
 */
@Document(indexName = "customer")
@Data
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    private String id;

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;//文章正文


    @ApiModelProperty(value = "客户类型代码")
    private String customerTypeId;

    @ApiModelProperty(value = "来源代码")
    private String lyId;

    @ApiModelProperty(value = "客户状态代码")
    private String customerStateId;

    @ApiModelProperty(value = "客户姓名")
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String customerName;

    @ApiModelProperty(value = "客户电话")
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String customerTel;

    @ApiModelProperty(value = "施工小区")
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String constructionDistrict;

    @ApiModelProperty(value = "客户地址")
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String customerAdd;

    @ApiModelProperty(value = "电子邮箱")
    private String customerEmail;

    @ApiModelProperty(value = "建档人uid")
    private String insertUserId;

    @ApiModelProperty(value = "建档人pid")
    private String insertPersonId;

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


    @ApiModelProperty(value = "客户负责人 ")
    private String customerContacts;

    @ApiModelProperty(value = "客户电话2")
    private String customerTel2;

    @ApiModelProperty(value = "客户负责人电话")
    private String customerContactsTel;

    @ApiModelProperty(value = "客户级别")
    private String customerLevelId;

  

}
