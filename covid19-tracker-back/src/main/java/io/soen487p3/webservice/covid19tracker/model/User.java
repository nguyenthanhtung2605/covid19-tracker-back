package io.soen487p3.webservice.covid19tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "auth_user")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(value = "user_id", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "auth_user_id")
    private int id;

    @NotNull(message="First name is compulsory")
    @JsonProperty(value = "first_name")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message="Last name is compulsory")
    @JsonProperty(value = "last_name")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message="Please provide a valid email")
    @Email(message = "Email is invalid")
    @JsonProperty(value = "email")
    @Column(name = "user_email")
    private String email;

    @Id
    @Length(min=8, message="Username should be at least 8 characters")
    @JsonProperty(value = "username")
    @Column(name = "user_nickname")
    private String userName;

    @NotNull(message="Password is compulsory")
    @Length(min=8, message="Password should be at least 5 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "user_password")
    private String password;

    @JsonProperty(value = "is_verified")
    @Column(name = "is_verified")
    private boolean isVerified;

    @JsonProperty(value = "is_verified")
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @JsonProperty(value = "roles")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
