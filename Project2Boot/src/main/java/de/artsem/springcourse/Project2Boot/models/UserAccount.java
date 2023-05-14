package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "useraccount")
public class UserAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "username")
    private String userAccountName;

    @Column(name = "password")
    private  String password;

    @Column(name = "role")
    private String role;

    public UserAccount() {
    }

    public UserAccount(String userName) {
        this.userAccountName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", userAccountName='" + userAccountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
