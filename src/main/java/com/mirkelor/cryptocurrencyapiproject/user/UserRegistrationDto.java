package com.mirkelor.cryptocurrencyapiproject.user;

import com.mirkelor.cryptocurrencyapiproject.entity.User;
import com.mirkelor.cryptocurrencyapiproject.validation.FieldMatch;
import com.mirkelor.cryptocurrencyapiproject.validation.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword",
        message = "The password fields must match")
})
public class UserRegistrationDto {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String email;

    private User user;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(User user){
        this.user = user;
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setMatchingPassword(getPassword());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
