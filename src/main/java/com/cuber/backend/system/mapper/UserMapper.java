package com.cuber.backend.system.mapper;

import com.cuber.backend.system.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author deyi
* @description 针对表【system_user(用户表)】的数据库操作Mapper
* @createDate 2023-05-04 15:34:12
* @Entity com.cuber.backend.system.model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




