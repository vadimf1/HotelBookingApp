package IntexSoft.Authorization.controllers;

import IntexSoft.Authorization.models.User;
import IntexSoft.Authorization.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.SQLException;


@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }

    @PostMapping("/signup")
    public String createUser(@RequestBody User user) throws Exception {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws IOException, SQLException {
        return userService.login(user);
    }

}


