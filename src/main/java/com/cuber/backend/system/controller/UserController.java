package com.cuber.backend.system.controller;

import com.auth0.jwt.JWT;
import com.cuber.backend.system.annotation.SystemLogging;
import com.cuber.backend.system.model.common.Result;
import com.cuber.backend.system.model.common.ResultUtils;
import com.cuber.backend.system.model.entity.User;
import com.cuber.backend.system.service.UserService;
import com.cuber.backend.system.utils.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/save")
    Result<String> save(@RequestBody User user){
        boolean saved = userService.save(user);
        return ResultUtils.success(String.valueOf(saved));
    }

    @GetMapping("/{id}")
    @SystemLogging(logResult = false)
    Result<User> getUserById(@PathVariable String id){
        User user = userService.getById(id);
        return ResultUtils.success(user);
    }

    @PostMapping("/login")
    Result<String> login(@RequestBody User user){
        userService.login(user);
        String token= JwtUtil.generateToken(user.getId(),user.getUsername());
        return ResultUtils.success(token);
    }

}
