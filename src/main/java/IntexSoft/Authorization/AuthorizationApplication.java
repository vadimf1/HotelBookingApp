package IntexSoft.Authorization;

import IntexSoft.Authorization.models.User;
import IntexSoft.Authorization.repository.UserDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AuthorizationApplication {

	public static void main(String[] args) {
		UserDAO AllUsers = new UserDAO();
		List<User> users = AllUsers.getAllUsers();
		for(var user: users){
			System.out.println(user.getUserId() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPassword());
		}
		SpringApplication.run(AuthorizationApplication.class, args);
	}

}
