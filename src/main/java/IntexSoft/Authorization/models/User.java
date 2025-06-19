package IntexSoft.Authorization.models;

import lombok.*;

@Data
public class User {
    private int UserId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    public User(){}
}
