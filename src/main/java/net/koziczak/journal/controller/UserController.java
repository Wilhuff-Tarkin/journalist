package net.koziczak.journal.controller;

import net.koziczak.journal.model.User;
import net.koziczak.journal.model.dto.UserDto;
import net.koziczak.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// nasłuchiwanie na żądania protkołu http
@RequestMapping("/")
@RestController
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obsługa żądań wypisania wszystkich użytkowników z DB
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Obsługa żądania rejestracji użytkownika
    @PostMapping("/register/{login}&{email}&{password}")
    public User addUser(
            @PathVariable String login,
            @PathVariable String email,
            @PathVariable String password
    ){
        return userService.addUser(new UserDto(login,email,password));
    }
    @GetMapping("/login_user/{email}&{password}")
    public String login(
            @PathVariable String email,
            @PathVariable String password
    ){
        return userService.loginUser(email,password);
    }



}
