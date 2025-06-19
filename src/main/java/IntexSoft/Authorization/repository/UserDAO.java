package IntexSoft.Authorization.repository;

import IntexSoft.Authorization.connect.DBConnection;
import IntexSoft.Authorization.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> getAllUsers(){
        String sql = "SELECT * FROM users";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();

            while(resultSet.next())
            {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(User user) throws Exception{
        String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES(?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();
        }
    }

    public boolean findUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try(Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            try(ResultSet result = statement.executeQuery()){
                return result.next(); // Возвращает true если найдёт совпадение
            }
        }
    }
}




