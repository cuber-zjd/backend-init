package com.cuber.backend.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuber.backend.system.model.entity.Permission;
import com.cuber.backend.system.service.PermissionService;
import com.cuber.backend.system.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author deyi
* @description 针对表【system_permission(权限表)】的数据库操作Service实现
* @createDate 2023-05-08 16:55:18
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




