package com.greatlearning.ems.service.impl;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.model.UserRequest;
import com.greatlearning.ems.repository.RoleRepository;
import com.greatlearning.ems.repository.UserRepository;
import com.greatlearning.ems.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRoleImpl implements UserRoleService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    public User saveUser(UserRequest request){
        User user = new User();
        user.setName(request.getUsername());
        user.setPassword(passwordEncoder().encode(request.getPassword()));
        request.getRoles().forEach(
                role->{
                    user.getRoles().add(roleRepository.findByName(role.getName()).get());
                }
        );
        return userRepository.save(user);
    }
}
