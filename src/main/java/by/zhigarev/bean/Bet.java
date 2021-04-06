package by.zhigarev.bean;

import java.util.Objects;

public class Bet {
    private int id;
    private Event event;
    private OutcomeType outcome;
    private BetStatus status;
    private double offer;

    public Bet(int id, Event event, OutcomeType outcome, BetStatus status, double offer) {
        this.id = id;
        this.event = event;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public OutcomeType getOutcome() {
        return outcome;
    }

    public void setOutcome(OutcomeType outcome) {
        this.outcome = outcome;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
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
        Bet bet = (Bet) o;
        return id == bet.id && Double.compare(bet.offer, offer) == 0 && Objects.equals(event, bet.event) && Objects.equals(outcome, bet.outcome) && Objects.equals(status, bet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, outcome, status, offer);
    }
}
