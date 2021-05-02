package by.zhigarev.bean.info;

import java.util.Date;
import java.util.Objects;

public class SignUpInfo {
    private String name;
    private String surName;
    private String login;
    private String password;
    private String email;
    private Date birthdayDate;

    public SignUpInfo(String name, String surName, String login, String password, String email, Date birthdayDate) {
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthdayDate = birthdayDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpInfo that = (SignUpInfo) o;
        return getLogin().equals(that.getLogin()) &&
                getPassword().equals(that.getPassword()) &&
                getName().equals(that.getName()) &&
                getSurName().equals(that.getSurName()) &&
                getEmail().equals(that.getEmail()) &&
                getBirthdayDate().equals(that.getBirthdayDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getName(), getSurName(), getEmail(), getBirthdayDate());
    }
}
