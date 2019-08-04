package com.itzhuo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName
public class Dept implements Serializable {
    private static final long serialVersionUID = 8520874477140773881L;

    private Integer deptNo;
    private String dname;
}
