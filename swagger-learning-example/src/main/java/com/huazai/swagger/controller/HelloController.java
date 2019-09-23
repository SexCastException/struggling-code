package com.huazai.swagger.controller;

import com.huazai.swagger.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping(value = "hello")
    public String hello(String s) {
        return s;
    }

    @ApiOperation("返回用户信息请求")
    @PostMapping("user")
    public User getUser() {
        return new User("huazai", "*******");
    }

    @GetMapping("test1")
    public String test1(@ApiParam("用户名") String username) {
        return "huazai@qq.com";
    }
}
