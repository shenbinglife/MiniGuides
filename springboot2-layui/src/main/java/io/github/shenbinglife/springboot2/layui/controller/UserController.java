package io.github.shenbinglife.springboot2.layui.controller;

import io.github.shenbinglife.springboot2.layui.entity.User;
import io.github.shenbinglife.springboot2.layui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Page<User> list(String name, String orgName, String mobilePhone,
                           @RequestParam(defaultValue = "20") Integer pageSize,
                           @RequestParam(defaultValue = "0") Integer pageNo) {
        return userService.page(name, mobilePhone, orgName, pageNo, pageSize);
    }
}
