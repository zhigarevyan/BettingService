package by.zhigarev.bean;

import java.util.Date;
import java.util.Objects;

public class Account {
    private int id;
    private int user;
    private double balance;
    private Date creation_date;

    public Account(int id, int user, double balance, Date creation_date) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.creation_date = creation_date;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user +
                ", balance=" + balance +
                ", creation_date=" + creation_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId() &&
                getUser() == account.getUser() &&
                Double.compare(account.getBalance(), getBalance()) == 0 &&
                getCreation_date().equals(account.getCreation_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getBalance(), getCreation_date());
    }
}
