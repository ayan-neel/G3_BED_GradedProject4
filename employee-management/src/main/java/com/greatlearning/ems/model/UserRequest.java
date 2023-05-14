package com.greatlearning.ems.model;

import com.greatlearning.ems.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    public String username;
    public String password;
    public List<Role> roles;

}
