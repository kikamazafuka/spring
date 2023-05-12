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

    public String getUserName() {
        return userAccountName;
    }

    public void setUserName(String userName) {
        this.userAccountName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", userName='" + userAccountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
