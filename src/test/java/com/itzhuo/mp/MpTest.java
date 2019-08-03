package com.itzhuo.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itzhuo.mapper.PersonMapper;
import com.itzhuo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    public void etestSelectList(){
        List<Person> people = personMapper.selectList(null);
        System.out.println(people);

    }

    @Test
    public void testInsert(){
        Person person = new Person();
        person.setName("刘红雨");
        person.setAge(32);
        person.setEmail("lhy@163.com");
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

    /**
     * selectByid
     */
    @Test
    public void selectById(){
        personMapper.selectById(128824816637083238L);
    }

    /**
     * selectbyids
     */
    @Test
    public void selectByIds(){


        personMapper.selectBatchIds(Arrays.asList("128824816637083238","1157581789317959681"));
    }

    /**
     * 名字包含雨 小于40
     *
     */
    @Test
    public void selectByQw(){
        QueryWrapper<Person> qw = new QueryWrapper<Person>();
        qw.like("name", "雨").lt("age", 40);
        personMapper.selectList(qw);
    }

    /**
     * 名字有雨，年龄大于20小于40，email 不为空
     */
    @Test
    public void selectByQw2(){
        QueryWrapper<Person> personQueryWrapper = new QueryWrapper<Person>();
        personQueryWrapper.like("name", "雨")
                .gt("age", 20)
                .lt("age", 40)
                .isNotNull("email");
        personMapper.selectList(personQueryWrapper);
    }

    /**
     * 姓王，或者age>25,age 降序，age相同，id升序
     */
    @Test
    public void selectByqw3(){

        LambdaQueryWrapper<Person> lambda = new QueryWrapper<Person>().lambda();
        LambdaQueryWrapper<Person> name = lambda.like(Person::getName, "王").or()
                .gt(Person::getAge,25).orderByDesc(Person::getAge).orderByAsc(Person::getId);

        personMapper.selectList(lambda);

    }

    /**
     * 创建日期为 并且上级名字姓王
     */
    @Test
    public void selectByQw4() throws ParseException {
        LambdaQueryWrapper<Person> lambda = Wrappers.lambdaQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2016-08-07 20:54:11");

        lambda.le(Person::getCreateTime,date).inSql(Person::getManagerId,"select id from person where name like '%王%'");
        personMapper.selectList(lambda);
    }

    /**
     * 王姓并且年龄小于40，或者email不为空
     */
    @Test
    public void selectByqw5(){
        LambdaQueryWrapper<Person> lambda = Wrappers.lambdaQuery();
        lambda.likeRight(Person::getName, "王").and(wq -> wq.lt(Person::getAge, 40).or().isNotNull(Person::getEmail));
        personMapper.selectList(lambda);
    }


    @Test
    public void selectByQw6(){
        QueryWrapper<Person> personQueryWrapper = new QueryWrapper<Person>();
        personQueryWrapper.like("name", "雨")
                .gt("age", 20)
                .lt("age", 40)
                .isNotNull("email");
        personMapper.selectAll(personQueryWrapper);
    }

    @Test
    public void selectByQwEntity(){
        Person person = new Person();

        person.setName("刘红雨");
        person.setAge(32);
        QueryWrapper<Person> personQueryWrapper = new QueryWrapper<>(person);
        personMapper.selectList(personQueryWrapper);
    }




}
