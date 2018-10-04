package com.tidc.parttimemonarch.dao;

import com.tidc.parttimemonarch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountDAO extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
