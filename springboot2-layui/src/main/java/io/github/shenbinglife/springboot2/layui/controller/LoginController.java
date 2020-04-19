package io.github.shenbinglife.springboot2.layui.controller;

import io.github.shenbinglife.springboot2.layui.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("longin")
    public Result<?> login(String username, String password){
        return Result.success();
    }

    @GetMapping("logout")
    public Result<?> logout(){
        return Result.success();
    }
}
