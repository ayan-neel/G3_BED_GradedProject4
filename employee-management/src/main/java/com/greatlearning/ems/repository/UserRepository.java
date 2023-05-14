package com.greatlearning.ems.repository;

import com.greatlearning.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByName(String name);
}
