package by.zhigarev.bean.info;

import java.util.Date;
import java.util.Objects;

public class UserBetInfo {
    private int id;
    private int accountId;
    private int betId;
    private Date timeStamp;
    private double betAmount;

    public UserBetInfo(int id, int accountId, int betId, Date timeStamp, double betAmount) {
        this.id = id;
        this.accountId = accountId;
        this.betId = betId;
        this.timeStamp = timeStamp;
        this.betAmount = betAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
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
        UserBetInfo that = (UserBetInfo) o;
        return id == that.id && accountId == that.accountId && betId == that.betId && Double.compare(that.betAmount, betAmount) == 0 && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, betId, timeStamp, betAmount);
    }
}
