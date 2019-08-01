package com.itzhuo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user")
public class User implements Serializable {
    private static Long serialId =1L;

    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
