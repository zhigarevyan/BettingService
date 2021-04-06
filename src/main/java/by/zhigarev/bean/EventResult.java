package by.zhigarev.bean;

import java.util.Objects;

public class EventResult {
    private int id;
    private Event event;
    private Participant winner;
    private int firstParticipantScore;
    private int secondParticipantScore;

    public EventResult(int id, Event event, Participant winner, int firstParticipantScore, int secondParticipantScore) {
        this.id = id;
        this.event = event;
        this.winner = winner;
        this.firstParticipantScore = firstParticipantScore;
        this.secondParticipantScore = secondParticipantScore;
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

    public Participant getWinner() {
        return winner;
    }

    public void setWinner(Participant winner) {
        this.winner = winner;
    }

    public int getFirstParticipantScore() {
        return firstParticipantScore;
    }

    public void setFirstParticipantScore(int firstParticipantScore) {
        this.firstParticipantScore = firstParticipantScore;
    }

    public int getSecondParticipantScore() {
        return secondParticipantScore;
    }

    public void setSecondParticipantScore(int secondParticipantScore) {
        this.secondParticipantScore = secondParticipantScore;
    }

    @Override
    public String toString() {
        return "EventResult{" +
                "id=" + id +
                ", event=" + event +
                ", winner=" + winner +
                ", winnerScore=" + firstParticipantScore +
                ", loserScore=" + secondParticipantScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventResult that = (EventResult) o;
        return id == that.id && firstParticipantScore == that.firstParticipantScore && secondParticipantScore == that.secondParticipantScore && Objects.equals(event, that.event) && Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, winner, firstParticipantScore, secondParticipantScore);
    }
}
