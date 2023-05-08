package com.cuber.backend.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuber.backend.system.service.RoleService;
import com.cuber.backend.system.model.entity.Role;
import com.cuber.backend.system.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author deyi
* @description 针对表【system_role(角色表)】的数据库操作Service实现
* @createDate 2023-05-08 16:55:18
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




