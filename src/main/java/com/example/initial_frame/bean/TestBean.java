package com.example.initial_frame.bean;




import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import javax.validation.constraints.*;
import com.example.initial_frame.common.validated.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Auther: SunDC
 * @Date: 2019/8/27 10:14
 * @Description: 測試實體類
 * @Null 被注释的元素必须为null
 * @NotNull 被注释的元素不能为null，可以为空字符串
 * @AssertTrue 被注释的元素必须为true
 * @AssertFalse 被注释的元素必须为false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max,min) 被注释的元素的大小必须在指定的范围内。
 * @Digits(integer,fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(value) 被注释的元素必须符合指定的正则表达式。
 * @Email 被注释的元素必须是电子邮件地址
 * @Length 被注释的字符串的大小必须在指定的范围内
 * @Range 被注释的元素必须在合适的范围内
 * @NotEmpty：用在集合类上，不能为null，并且长度必须大于0
 * @NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
 */
@Data
public class TestBean {

    @NotBlank(message = "姓名  不能爲空", groups = {Insert.class })
    private String name ;
    @Min(value = 1, message = "最小值为1",groups = {Insert.class }) /* 最小值为1*/
    @Max(value = 88, message = "最大值为88",groups = {Insert.class }) /* 最大值88*/
    @NotNull(message = "年龄  不能爲空", groups = {Insert.class })
    private Integer age ;

    @NotNull(message = "主鍵id  不能爲空", groups = {Update.class })
    private Long id;

    @Pattern(regexp = "[0-9-()（）]{7,18}", groups = {Update.class}, message = "请输入指定电话号")
    private String  phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")   /*接收时转换*/
    private Date createTime;

    @Future(message = "expireTime is not less than now", groups = {Update.class})
    @NotNull(message = "expireTime is not null", groups = {Update.class})
    private Date expireTime;

    @Future(message = "需要一个将来日期")/*只能是将来的日期*/
    /* @Past :只能去过去的日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")/*发出时转换*/
    private Date date;

    @NotNull
    @DecimalMin(value = "0.1") /* 最小值0.1元*/
    @DecimalMax(value = "10000.00")  /* 最大值10000元*/
    private Double doubleValue = null;

    @Min(value = 1, message = "最小值为1") /* 最小值为1*/
    @Max(value = 88, message = "最大值为88") /* 最大值88*/
    private Integer integer;

    @Email(message = "邮箱格式错误")/* 邮箱验证*/
    private String email;

    @Size(min = 20, max = 30, groups = {Insert.class},message = "字符串长度要求20到30之间。")
    private String size;

    @Range(min = 1, max = 888, message = "范围为1至888")  /* 限定范围*/
    private Long range;

}
