package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.model.UserRequest;
import com.greatlearning.ems.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRoleService userRoleService;

    @PostMapping("/user/role")
    public Role addRole(@RequestBody Role role){
        return userRoleService.saveRole(role);
    }
    @PostMapping("/user/")
    public User addUser(@RequestBody UserRequest request){
        return userRoleService.saveUser(request);
    }



}
