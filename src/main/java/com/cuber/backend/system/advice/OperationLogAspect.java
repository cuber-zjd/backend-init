package com.cuber.backend.system.advice;

import com.alibaba.fastjson.JSON;
import com.cuber.backend.common.utils.HttpUtils;
import com.cuber.backend.common.utils.SecurityUtils;
import com.cuber.backend.system.annotation.OperationLogging;
import com.cuber.backend.system.model.entity.OperationLog;
import com.cuber.backend.system.service.OperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
public class OperationLogAspect {

    @Resource
    private OperationLogService operationLogService;

    private final ThreadLocal<LocalDateTime> startTime = new ThreadLocal<>();

    @Before("@annotation(operationLogging)")
    public void before(OperationLogging operationLogging) {
        startTime.set(LocalDateTime.now());
    }

    @AfterReturning(pointcut = "@annotation(operationLogging)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, OperationLogging operationLogging, Object result) {
        String resultStr = JSON.toJSONString(result);
        buildAndSaveLog(joinPoint, operationLogging, resultStr);
    }

    @AfterThrowing(pointcut = "@annotation(operationLogging)", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, OperationLogging operationLogging, Exception e) {
        String resultStr = e.getMessage();
        // 保留前2000个字符
        if (resultStr.length() > 2000) {
            resultStr = resultStr.substring(0, 2000);
        }
        buildAndSaveLog(joinPoint, operationLogging, resultStr);
    }

    @Transactional(rollbackFor = Exception.class)
    public void buildAndSaveLog(JoinPoint joinPoint, OperationLogging operationLogging, String resultStr) {
        OperationLog operationLog = new OperationLog();
        operationLog.setDescription(operationLogging.description()==null?operationLogging.value(): operationLogging.description());
        HttpUtils.getRequest()
                .map(HttpUtils::getIpAddress)
                .ifPresent(operationLog::setIp);
        operationLog.setMethod(joinPoint.getSignature().getName());
        operationLog.setOperationType(operationLogging.type().ordinal());
        operationLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setResult(resultStr);
        operationLog.setTime(startTime.get());
        operationLog.setDuration(Duration.between(startTime.get(), LocalDateTime.now()).toMillis());
        operationLog.setUsername(SecurityUtils.getLoginUsername());
        operationLogService.save(operationLog);
    }

}
