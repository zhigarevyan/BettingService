package by.zhigarev.bean;

import java.util.Date;
import java.util.Objects;

public class UserBet {
    private int id;
    private Account account;
    private Bet bet;
    private Date timeStamp;
    private double betAmount;

    public UserBet(int id, Account account, Bet bet, Date timeStamp, double betAmount) {
        this.id = id;
        this.account = account;
        this.bet = bet;
        this.timeStamp = timeStamp;
        this.betAmount = betAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBet userBet = (UserBet) o;
        return id == userBet.id && Double.compare(userBet.betAmount, betAmount) == 0 && Objects.equals(account, userBet.account) && Objects.equals(bet, userBet.bet) && Objects.equals(timeStamp, userBet.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, bet, timeStamp, betAmount);
    }
}
