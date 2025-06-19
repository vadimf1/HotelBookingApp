package IntexSoft.Authorization.services;

import IntexSoft.Authorization.models.User;
import IntexSoft.Authorization.repository.UserDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public String login(User user) throws SQLException {
        return checkLogin(user);
    }

    private String checkLogin(User user) throws SQLException {
        if(user.getEmail() == null || user.getPassword() == null) {
            return "Вы не ввели почту или пароль";
        }
        if(!userDAO.findUser(user.getEmail(), user.getPassword())){
            return "Проверьте корректность введённых даннных";
        }
        return "Авторизация прошла успешно";
    }

    public String signup(User user) throws Exception {
        return checkSignup(user);
    }

    private String checkSignup(User user) throws Exception {
        if(userDAO.findUser(user.getEmail(), user.getPassword())){
            return "Пользователь с такой почтой уже существует";
        }

        //Сделать валидацию на почту

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
//        UserDAO userDAO = new UserDAO();
//        userDAO.createUser(user);
        return "Регистрация прошла успешно!";
    }
}


