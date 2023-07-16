package com.demo.stocks.services;

import com.demo.stocks.dto.UserDTO;
import com.demo.stocks.model.User;
import com.demo.stocks.repository.UserRepository;
import com.demo.stocks.utilities.constants.ErrorMessage;
import com.demo.stocks.utilities.constants.enums.UserDetail;
import com.demo.stocks.utilities.exceptions.IncorrectPasswordException;
import com.demo.stocks.utilities.exceptions.UserAlreadyExistsException;
import com.demo.stocks.utilities.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public boolean isValidEmail(String email) throws Exception {
        var user = this.userRepository.findByEmail(email);
        return user == null;
    }

    private User fetchUser(int id) throws UserNotFoundException {
        User user = this.userRepository.findById(id);
        if (user == null)
            throw new UserNotFoundException(String.format("%s%d", ErrorMessage.USER_DOES_NOT_EXIST, id));

        return user;
    }

    public HttpStatus authenticateUser(int id, String password) throws UserNotFoundException, IncorrectPasswordException {

        // Get user from DB.
        User user = this.userRepository.findById(id);
        if (user == null)
            throw new UserNotFoundException(String.format("%s%d", ErrorMessage.USER_DOES_NOT_EXIST, id));

        // Check password.
        if (!bcrypt.matches(password, user.getPassword()))
            throw new IncorrectPasswordException("The password you entered is incorrect");

        return HttpStatus.OK;
    }

    public UserDTO getUser(int id) throws UserNotFoundException {
        // Get user from DB.
        User user = fetchUser(id);
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public HttpStatus saveUser(User user) throws Exception {
        boolean isValid = this.isValidEmail(user.getEmail());
        if (!isValid)
            throw new UserAlreadyExistsException(ErrorMessage.USER_EXISTS);

        user.setPassword(bcrypt.encode(user.getPassword()));
        this.userRepository.save(user);

        return HttpStatus.OK;
    }

    public HttpStatus updateUser(int id, UserDetail detail, String newEntry) throws UserNotFoundException {
        User user = this.fetchUser(id);
        switch (detail) {
            case PASSWORD -> user.setPassword(newEntry);
            case FIRST_NAME -> user.setFirstName(newEntry);
            case LAST_NAME -> user.setLastName(newEntry);
        }
        this.userRepository.save(user);
        return HttpStatus.OK;
    }

    public HttpStatus deactivateUser(int id) throws UserNotFoundException {
        this.getUser(id);
        this.userRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
