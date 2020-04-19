package io.github.shenbinglife.springboot2.layui.controller;

import io.github.shenbinglife.springboot2.layui.entity.User;
import io.github.shenbinglife.springboot2.layui.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> list(String name, String orgName, String mobilePhone,
                           @RequestParam(defaultValue = "20") Integer pageSize,
                           @RequestParam(defaultValue = "0") Integer pageNo) {
        return userService.page(name, mobilePhone, orgName, pageNo, pageSize);
    }

    @GetMapping("{userId}")
    public User getById(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @PostMapping()
    public void update(User user) {
        userService.update(user);
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable("userId") Long userId) {
        userService.deleteById(userId);
    }


}
