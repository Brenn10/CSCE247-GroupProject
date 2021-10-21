public abstract class User {
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected boolean approved;

  
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        
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