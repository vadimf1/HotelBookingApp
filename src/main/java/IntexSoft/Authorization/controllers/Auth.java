package IntexSoft.Authorization.controllers;

import IntexSoft.Authorization.kafka.KafkaProducer;
import IntexSoft.Authorization.models.User;
import IntexSoft.Authorization.repository.UserDAO;
import IntexSoft.Authorization.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.SQLException;


@RestController
@RequestMapping("api/users")
public class Auth {

    private final UserService userService = new UserService();

    @Autowired
    private final KafkaProducer kafkaProducer;
    public Auth(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/signup")
    public String createUser(@RequestBody User user) throws Exception {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws IOException, SQLException {
        kafkaProducer.sendMessage(user);
        return userService.login(user);
    }

}


