package com.cuber.backend.system.mapper;
import java.util.Date;

import com.cuber.backend.system.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void save(){
        User user = new User();
        user.setId(0L);
        user.setUsername("root");
        user.setPassword("123");
        user.setIdCard("");
        user.setEmail("");
        user.setPhone("");
        user.setBirthday(new Date());
        user.setStatus(0);
        user.setLastLogin(new Date());
        user.setCreatedBy(0L);
        user.setCreateTime(new Date());
        user.setUpdateBy(0L);
        user.setUpdateTime(new Date());
        user.setIsDeleted(0);
        user.setRemark("");

        int insert = userMapper.insert(user);

    }

    @Test
    void testFind(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setUsername("admin");
        user.setId(1L);
        int i = userMapper.updateById(user);
    }

    @Test
    void testDelete(){
        int i = userMapper.deleteById(1);
    }

}