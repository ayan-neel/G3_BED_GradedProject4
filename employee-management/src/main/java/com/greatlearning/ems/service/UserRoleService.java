package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.model.UserRequest;

public interface UserRoleService {
    public Role saveRole(Role role);
    public User saveUser(UserRequest user);
}
