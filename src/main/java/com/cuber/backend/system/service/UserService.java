package com.cuber.backend.system.service;

import com.cuber.backend.system.annotation.OperationLogging;
import com.cuber.backend.system.enums.OperationType;
import com.cuber.backend.system.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author deyi
* @description 针对表【system_user(用户表)】的数据库操作Service
* @createDate 2023-05-04 15:34:12
*/
public interface UserService extends IService<User> {


    void login(User user);
}
