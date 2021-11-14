/** Hosts all basic functions of each user, such as firstname and Lastname
 * @author Ian McDevitt
 */
package dataTypes;
import java.util.UUID;
import database.Database;


public abstract class User {
    protected UUID id;
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected boolean approved;
    protected boolean removed;

    public User(UUID id,
                String username, 
                String password, 
                String email,
                String firstName,
                String lastName,
                boolean approved,
                boolean removed) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.approved = approved;
        this.removed = removed;
    }
    /**
     * Checks if the email is a ".sc.edu"/ school email
     * @param email
     * @return the email is a school email
     */
    public static boolean isEmailValid(String email) {
        return email.contains("email.sc.edu");
    }
    /**
     * Verifies the username matches the user's username, and the 
     * same for the password
     * @param username
     * @param password
     * @return if the params match
     */
    protected boolean isPasswordValid(String username, String password) {
         return this.username == username && this.password == password;
    }
    /**
     * Returns the UUID for this user
     * @return id
     */
    public UUID getId() {
        return id;
    }
    /**
     * Returns the Username for this user
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Returns the Password for this user
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Returns the Email for this user
     * @return email
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Returns the firstname for this user
     * @return firstname
     */
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * Returns the lastname for this user
     * @return lastname
     */
    public String getLastName() {
        return this.lastName;
    }
    /**
     * Returns the full name for this user
     * @return firstname + lastname
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    /**
     * Returns the approval for this account
     * @return approved
     */
    public boolean isApproved() {
        return this.approved;
    }
    /**
     * Sets the approval for this account
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    /**
     * Returns if this account is removed
     * @return removed
     */
    public boolean isRemoved() {
        return this.removed;
    }
    /**
     * Sets if this account is removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public abstract String toString();

}