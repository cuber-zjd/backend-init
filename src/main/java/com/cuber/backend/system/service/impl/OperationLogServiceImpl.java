package com.cuber.backend.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuber.backend.system.service.OperationLogService;
import com.cuber.backend.system.model.entity.OperationLog;
import com.cuber.backend.system.mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

/**
* @author deyi
* @description 针对表【system_operation_log(系统操作日志)】的数据库操作Service实现
* @createDate 2023-05-08 21:07:19
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
    implements OperationLogService {

}




