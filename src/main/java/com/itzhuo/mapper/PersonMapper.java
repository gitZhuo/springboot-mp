package com.itzhuo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.itzhuo.model.Person;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PersonMapper extends BaseMapper<Person> {
    @Select("select * from person ${ew.customSqlSegment}")
    List<Person> selectAll(@Param(Constants.WRAPPER) Wrapper<Person> wrapper);

    @Select("select p.*,d.deptno dno,d.dname dname from person p left join dept d on d.deptno=p.deptno   ${ew.customSqlSegment}")
    @Results(id = "personMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "dno",property = "dept.deptNo"),
            @Result(column = "dname",property = "dept.dname"),

    })
    List<Person> selectJoin(IPage iPage, @Param(Constants.WRAPPER) Wrapper wrapper);
}
