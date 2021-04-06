package by.zhigarev.bean.info;

import java.util.Objects;

public class BetInfo {
    private int id;
    private int eventId;
    private int outcome;
    private int status;
    private double offer;

    public BetInfo(int id, int eventId, int outcome, int status, double offer) {
        this.id = id;
        this.eventId = eventId;
        this.outcome = outcome;
        this.status = status;
        this.offer = offer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetInfo betInfo = (BetInfo) o;
        return id == betInfo.id && eventId == betInfo.eventId && outcome == betInfo.outcome && status == betInfo.status && Double.compare(betInfo.offer, offer) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, outcome, status, offer);
    }
}
