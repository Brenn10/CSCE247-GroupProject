import java.util.UUID;

public abstract class User {
    protected UUID id;
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected boolean approved;

    public User(UUID id,
                String username, 
                String password, 
                String email,
                String firstName,
                String lastName,
                boolean approved) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.approved = approved;
    }

    public static boolean isEmailValid(String email) {
        return email.contains("email.sc.edu");
    }
    protected boolean isPasswordValid(String username, String password) {
         return this.username == username && this.password == password;
    }

    protected String getUsername() {
        return this.username;
    }

    protected String getPassword() {
        return this.password;
    }
    
    protected String getEmail() {
        return this.email;
    }

    protected String getFirstName() {
        return this.firstName;
    }

    protected String getLastName() {
        return this.lastName;
    }


}