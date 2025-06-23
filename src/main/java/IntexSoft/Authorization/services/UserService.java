package IntexSoft.Authorization.services;

import IntexSoft.Authorization.models.User;
import IntexSoft.Authorization.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(User user) throws SQLException {
        return checkLogin(user);
    }

    private String checkLogin(User user) throws SQLException {
        if(user.getEmail() == null || user.getPassword() == null) {
            return "Вы не ввели почту или пароль";
        }
        if(!userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword())){
            return "Проверьте корректность введённых даннных";
        }
        return "Авторизация прошла успешно";
    }

    public String signup(User user) throws Exception {
        return checkSignup(user);
    }

    private String checkSignup(User user) throws Exception {
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
        user.setPassword(password);

        userRepository.save(user);
    }

}




