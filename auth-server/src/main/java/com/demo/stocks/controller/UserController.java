package com.demo.stocks.controller;

import com.demo.stocks.dto.UserDTO;
import com.demo.stocks.model.User;
import com.demo.stocks.services.UserService;
import com.demo.stocks.utilities.constants.enums.UserDetail;
import com.demo.stocks.utilities.exceptions.IncorrectPasswordException;
import com.demo.stocks.utilities.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public HttpStatus registerUser(@Valid @RequestBody User user) throws Exception {
        return this.userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) throws UserNotFoundException {
        return this.userService.getUser(id);
    }

    @GetMapping("/auth/{id}")
    public HttpStatus authenticateUser(@PathVariable int id, String password) throws UserNotFoundException, IncorrectPasswordException {
        return this.userService.authenticateUser(id, password);
    }

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
