package com.itzhuo.mp;

import com.itzhuo.mapper.UserMapper;
import com.itzhuo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MpTest {

    @Autowired
    UserMapper userMapper;
    @Test
    public void test1(){
        User user = userMapper.selectById(2);
        System.out.println(user);
    }

    @Test
    public void testSelectList(){
        List<User> userList = userMapper.selectList(null);

    }


}
