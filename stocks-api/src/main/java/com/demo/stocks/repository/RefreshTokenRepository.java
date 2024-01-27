package com.demo.stocks.repository;

import com.demo.stocks.model.RefreshToken;
import com.demo.stocks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    int deleteByUser(User user);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken findByUserId(int id);
}
