package com.itzhuo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "PERSON")
public class Person implements Serializable {
    private static final long serialVersionUID = -7953265230054315613L;
    private static Long serialId = 1L;

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private Date createTime;

    private Dept dept;


}