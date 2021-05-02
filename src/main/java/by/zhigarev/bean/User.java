package by.zhigarev.bean;

import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surName;
    private String email;
    private Date birthdayDate;
    private int role;

    public User(int id, String login, String password, String name, String surName, String email, Date birthdayDate, int role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getRole() == user.getRole() &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                getName().equals(user.getName()) &&
                getSurName().equals(user.getSurName()) &&
                getEmail().equals(user.getEmail()) &&
                getBirthdayDate().equals(user.getBirthdayDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getName(), getSurName(), getEmail(), getBirthdayDate(), getRole());
    }
}
