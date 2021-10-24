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
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}