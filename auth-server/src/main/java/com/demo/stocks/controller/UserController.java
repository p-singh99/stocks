package com.demo.stocks.controller;

import com.demo.stocks.model.*;
import com.demo.stocks.model.httpResponse.HttpError;
import com.demo.stocks.model.httpResponse.HttpResponse;
import com.demo.stocks.model.httpResponse.HttpResponseBuilder;
import com.demo.stocks.model.httpResponse.ResponseGenerator;
import com.demo.stocks.services.JwtService;
import com.demo.stocks.services.RefreshTokenService;
import com.demo.stocks.services.UserService;
import com.demo.stocks.utilities.constants.enums.HttpResponseConstants;
import com.demo.stocks.utilities.constants.enums.UserDetail;
import com.demo.stocks.utilities.exceptions.IncorrectPasswordException;
import com.demo.stocks.utilities.exceptions.UserAlreadyExistsException;
import com.demo.stocks.utilities.exceptions.UserNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}", allowCredentials = "true", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RequestMapping("/auth")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, HttpServletResponse response) {
        try {
            User regiseredUser = this.userService.saveUser(user);
            refreshTokenService.generateRefreshToken(regiseredUser.getId());
            response.addCookie(jwtService.generateJwtToken(regiseredUser.getUsername()));
            return ResponseEntity
                    .ok()
                    .body(new JwtResponse(
                                regiseredUser.getId(),
                                regiseredUser.getUsername()
                        ));
        } catch (UserAlreadyExistsException e) {
            System.out.println("Exception occured while registering user, " + e.toString());
        }
        ArrayList<HttpError> errors = new ArrayList<HttpError>();
        errors.add(new HttpError(HttpResponseConstants.USER_ALREADY_EXISTS.toString(), "username", "User already exists"));
        ResponseGenerator res = new ResponseGenerator(new HttpResponseBuilder());
        return ResponseEntity.badRequest().body(res.createBadRequestResponse(HttpResponseConstants.USER_ALREADY_EXISTS.toString(), errors));
    }

//    @PostMapping()
//    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> request) {
//        ResponseGenerator generator = new ResponseGenerator(new HttpResponseBuilder());
//        HttpResponse response;
//        try {
//            User user = this.userService.authenticateUser(request.get("email"), request.get("password"));
//
//            String jwtToken = jwtService.generateJwtToken(user.getUsername());
//            System.out.println("--------------- JWT: " + jwtToken + " -------------");
//            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user.getId());
//            System.out.println("--------------- RefreshToken: " + refreshToken.getToken() + " -------------");
//            return ResponseEntity.ok(
//                    new JwtResponse(
//                            jwtToken,
//                            user.getId(),
//                            user.getEmail()
//                    ));
//        } catch (UserNotFoundException e) {
//            ArrayList<HttpError> errors = new ArrayList<HttpError>();
//            errors.add(new HttpError(HttpResponseConstants.USER_NOT_FOUND.toString(), "username", HttpResponseConstants.USER_NOT_FOUND.getResponse()));
//            response = generator.createBadRequestResponse(HttpResponseConstants.USER_NOT_FOUND.toString(), errors);
//        } catch (IncorrectPasswordException e) {
//            ArrayList<HttpError> errors = new ArrayList<HttpError>();
//            errors.add(new HttpError(HttpResponseConstants.INCORRECT_USER_DETAILS.toString(), "password", HttpResponseConstants.INCORRECT_USER_DETAILS.getResponse()));
//            response = generator.createBadRequestResponse(HttpResponseConstants.INCORRECT_USER_DETAILS.toString(), errors);
//        }
//        return ResponseEntity.badRequest().body(response);
//    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
//        ResponseGenerator generator = new ResponseGenerator(new HttpResponseBuilder());
//        HttpResponse response;
//        String refreshTokenVal = request.get("refreshToken");
//        Optional<RefreshToken> token = refreshTokenService.findByToken(refreshTokenVal);
//        System.out.println("Entered function");
//        if (token.isPresent()) {
//            System.out.println("Token is present");
//            try {
//                RefreshToken refreshToken = refreshTokenService.verifyToken(token.get());
//                User user = refreshToken.getUser();
//                String jwtToken = jwtService.generateJwtToken(user.getUsername());
//                System.out.println("Token is valid");
//                return ResponseEntity.ok(new JwtResponse(jwtToken, refreshToken.getToken(), user.getId(), user.getEmail()));
//            } catch (RefreshTokenExpiredException e) {
//                ArrayList<HttpError> errors = new ArrayList<HttpError>();
//                errors.add(new HttpError(HttpResponseConstants.SESSION_EXPIRED.toString(), "refreshtoken", HttpResponseConstants.SESSION_EXPIRED.getResponse()));
//                response = generator.createBadRequestResponse(HttpResponseConstants.SESSION_EXPIRED.toString(), errors);
//                System.out.println("Token is expired");
//                return ResponseEntity.badRequest().body(response);
//            }
//        }
//        System.out.println("Token is not present");
//        return ResponseEntity.badRequest().body(HttpStatus.UNAUTHORIZED);
//    }

    @PutMapping("/{id}/first-name")
    public HttpStatus updateFirstName(@PathVariable int id, @RequestBody String firstName) throws UserNotFoundException {
        return this.userService.updateUser(id, UserDetail.FIRST_NAME, firstName);
    }

    @PutMapping("/{id}/last-name")
    public HttpStatus updateLastName(@PathVariable int id, @RequestBody String lastName) throws UserNotFoundException {
        return this.userService.updateUser(id, UserDetail.LAST_NAME, lastName);
    }

    @PutMapping("/{id}/password")
    public HttpStatus updatePassword(@PathVariable int id, @RequestBody String password) throws UserNotFoundException {
        return this.userService.updateUser(id, UserDetail.PASSWORD, password);
    }

    @DeleteMapping("/{id}/deactivate")
    public HttpStatus deactivateUser(@PathVariable int id) throws UserNotFoundException {
        return this.userService.deactivateUser(id);
    }
}
