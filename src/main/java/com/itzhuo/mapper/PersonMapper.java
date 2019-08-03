package com.itzhuo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.itzhuo.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PersonMapper extends BaseMapper<Person> {
    @Select("select * from person ${ew.customSqlSegment}")
    List<Person> selectAll(@Param(Constants.WRAPPER) Wrapper<Person> wrapper    );
}
