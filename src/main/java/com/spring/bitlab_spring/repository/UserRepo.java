package com.spring.bitlab_spring.repository;

import com.spring.bitlab_spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

}
