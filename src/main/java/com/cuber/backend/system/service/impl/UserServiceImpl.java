package com.cuber.backend.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuber.backend.system.annotation.OperationLogging;
import com.cuber.backend.system.enums.OperationType;
import com.cuber.backend.system.exception.BusinessException;
import com.cuber.backend.system.model.entity.User;
import com.cuber.backend.system.service.UserService;
import com.cuber.backend.system.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author deyi
* @description 针对表【system_user(用户表)】的数据库操作Service实现
* @createDate 2023-05-04 15:34:12
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    @OperationLogging(description = "登录",type = OperationType.OTHER)
    public void login(User user) {
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        try {
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new BusinessException(40102,"用户名或密码错误");
        }
    }

    public boolean save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userMapper.insert(user)>0;
    }
}




