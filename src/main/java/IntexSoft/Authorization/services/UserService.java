package IntexSoft.Authorization.services;

import IntexSoft.Authorization.kafka.KafkaProducer;
import IntexSoft.Authorization.models.User;
import IntexSoft.Authorization.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Key;
import java.sql.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final KafkaProducer kafkaProducer;


    public UserService(UserRepository userRepository, KafkaProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public String login(User user) throws IOException {
        return checkLogin(user);
    }

    private String checkLogin(User user) throws IOException {

        if(user.getEmail() == null || user.getPassword() == null) {
            return "Вы не ввели почту или пароль";
        }

        if(!userRepository.existsByEmail(user.getEmail())){
            return "Не существует пользователя с данной почтой";
        }

        User userInTable = userRepository.findByEmail(user.getEmail());
        String passwordUserInTable = userInTable.getPassword();

        boolean isPasswordCorrect = encoder.matches(user.getPassword(), passwordUserInTable);

        if(!isPasswordCorrect){
            return "Вы ввели неверный пароль";
        }

        kafkaProducer.sendMessage(generateToken(userInTable.getUserId()));
        return "Авторизация прошла успешно ";
    }

    public String signup(User user){
        return checkSignup(user);
    }

    private String checkSignup(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            return "Пользователь с такой почтой уже существует";
        }

        if(user.getEmail() == null || user.getPassword() == null) {
            return "Вы не ввели почту или пароль" + user.getEmail() + " " + user.getPassword();
        }
        if(user.getPassword().length() < 8)
        {
            return "Длина пароля должна быть не менее 8 символов";
        }
        if(user.getFirstName() == null || user.getLastName() == null)
        {
            return "Введите имя и фамилию";
        }
        createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return "Регистрация прошла успешно!";
    }

    @Transactional
    public void createUser(String firstName, String lastName, String email, String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashPassword(password));
        userRepository.save(user);
    }

    public String hashPassword(String password){
        return encoder.encode(password);
    }

    public String generateToken(int userId){
        Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        long EXPIRATION_TIME = 3600000;
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

}




