package com.demo.stocks.config;

import com.demo.stocks.dto.UserDTO;
import com.demo.stocks.services.JwtService;
import com.demo.stocks.services.RefreshTokenService;
import com.demo.stocks.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Value("${stocks.app.jwtCookieName}")
    private String jwtCookieName;

    private static String userIdCookieName = "user";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Cookie jwtCookie = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(jwtCookieName)) {
                    jwtCookie = cookie;
                }
            }

            if (jwtCookie != null) {
                String email = jwtService.getUsername(jwtCookie.getValue());

                // Verify the authentication token
                if (jwtService.validateJwtToken(jwtCookie.getValue())) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    this.resetAuthenticationAfterRequest();
                } else {
                    // Check if JWT token can be refreshed.
                    UserDTO user = null;
                    try {
                        user = userService.getUser(email);
                        boolean isValidSession = refreshTokenService.verifySession(user.getId());
                        if (isValidSession) {
                            response.addCookie(jwtService.generateJwtToken(user.getEmail()));
                            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            this.resetAuthenticationAfterRequest();
                        } else {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                    } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
