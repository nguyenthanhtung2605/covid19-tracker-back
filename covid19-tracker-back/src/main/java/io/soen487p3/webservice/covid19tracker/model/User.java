package io.soen487p3.webservice.covid19tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "auth_user")
public class User {
    @Id
    //nếu sử dụng GenerationType.IDENTITY sẽ bị lội
    // javax.persistence.PersistenceException: org.hibernate.exception.SQLGrammarException: error performing isolated work
    // solved it by changing to "@GeneratedValue(strategy=GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "user_id", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "auth_user_id")
    private int id;

    @NotEmpty(message = "Please provide your first name")
    @JsonProperty(value = "first_name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Please provide your last name")
    @JsonProperty(value = "last_name")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    @JsonProperty(value = "email")
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;


    @Length(min=5, message="Username should be at least 5 characters")
    @JsonProperty(value = "username")
    @Column(name = "user_nickname", nullable = false, unique = true)
    private String userName;

    @NotEmpty(message="Password is compulsory")
    @Length(min=5, message="Password should be at least 5 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "user_password")
    private String password;

    @JsonProperty(value = "is_verified")
    @Column(name = "is_verified")
    private boolean isVerified;

    @JsonProperty(value = "is_enabled")
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @JsonProperty(value = "roles")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
