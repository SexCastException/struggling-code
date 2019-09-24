package com.huazai.swagger.controller;

import com.huazai.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试接口",tags = {"测试接口"})
@RestController
public class HelloController {
    @ApiOperation("hello请求")
    @GetMapping(value = "hello")
    public String hello(String s) {
        return s;
    }

    @ApiOperation("用户登录请求")
    @PostMapping("login")
    public User login(@ApiParam("dfaf") User user) {
        return user;
    }

    @ApiOperation("测试请求")
    @GetMapping("test1")
    public String test1(@ApiParam("用户名") String username) {
        return "huazai@qq.com";
    }
}
