package by.zhigarev.bean.info;

import java.util.Objects;

public class EventResultInfo {
    private int id;
    private int eventId;
    private int winner;
    private int firstParticipantScore;
    private int secondParticipantScore;

    public EventResultInfo(int id, int eventId, int winner, int firstParticipantScore, int secondParticipantScore) {
        this.id = id;
        this.eventId = eventId;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventResultInfo that = (EventResultInfo) o;
        return id == that.id && eventId == that.eventId && winner == that.winner && firstParticipantScore == that.firstParticipantScore && secondParticipantScore == that.secondParticipantScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, winner, firstParticipantScore, secondParticipantScore);
    }
}
