package by.zhigarev.bean;

import java.util.Objects;

public class BetStatus {
    private int id;
    private String status;

    public BetStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetStatus betStatus = (BetStatus) o;
        return id == betStatus.id && Objects.equals(status, betStatus.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
