package com.itzhuo.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itzhuo.mapper.PersonMapper;
import com.itzhuo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MpTest {

    @Autowired
    PersonMapper personMapper;
    @Test
    public void test1(){
        Person person = personMapper.selectById(2);
        System.out.println(person);
    }

    @Test
    public void testSelectList(){
        List<Person> people = personMapper.selectList(null);
        System.out.println(people);

    }

    @Test
    public void testInsert(){
        Person person = new Person();
        person.setName("刘明强");
        person.setAge(26);
        person.setEmail("lmq@163.com");
        person.setManagerId(128824816637083238L);
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.YEAR,-3);

        person.setCreateTime(instance.getTime());

        personMapper.insert(person);
    }

    @Test
    public void testSelect(){
        Date date = new Date();

        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH,-1);
        Date time = instance.getTime();
        instance.add(Calendar.YEAR,-8);
        Date time1 = instance.getTime();

        System.out.println("======================================9==="+time);
        System.out.println("======================================9==="+time1);
        personMapper.selectList(new QueryWrapper<Person>()
                /*.lt("create_time",time)
                .ge("create_time",time1)*/
                .like("name","天")
                .between("create_time",time1,time)


        );

    }
}
