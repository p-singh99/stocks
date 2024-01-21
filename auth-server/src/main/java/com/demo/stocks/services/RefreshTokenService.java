package com.demo.stocks.services;

import com.demo.stocks.model.RefreshToken;
import com.demo.stocks.repository.RefreshTokenRepository;
import com.demo.stocks.repository.UserRepository;
import com.demo.stocks.utilities.exceptions.RefreshTokenExpiredException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${stocks.app.jwtRefreshExpiry}")
    private int refreshExpiryDuration;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken generateRefreshToken(int userId) {
        RefreshToken token = new RefreshToken();

        token.setUser(userRepository.findById(userId).get());
        token.setExpiryDate(Instant.now().plusMillis(this.refreshExpiryDuration));
        token.setToken(UUID.randomUUID().toString());
        System.out.println("Refresh Token value: " + token.getToken());
        System.out.println("User: " + token.getUser());
        System.out.println("Expiry: " + token.getExpiryDate());
        System.out.println("Generated Id: " + token.getId());
        return refreshTokenRepository.save(token);
    }

    public RefreshToken verifyToken(RefreshToken token) throws RefreshTokenExpiredException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenExpiredException("Refresh token expired. Please Sign In");
        }
        return token;
    }

    public boolean verifySession(int userId) {
        RefreshToken token = refreshTokenRepository.findByUserId(userId);
        if (token != null) {
            try {
                verifyToken(token);
            } catch (RefreshTokenExpiredException e) {
                deleteByUserId(userId);
                return false;
            }
            return true;
        }
        return false;
    }

    public RefreshToken getRefreshToken(int userID) {
        RefreshToken token = refreshTokenRepository.findByUserId(userID);
        return token;
    }

    @Transactional
    public int deleteByUserId(int userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
