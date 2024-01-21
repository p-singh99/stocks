package com.demo.stocks.repository;

import com.demo.stocks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findById(int id);
    User deleteById(int id);
    User deleteByEmail(String email);

}
