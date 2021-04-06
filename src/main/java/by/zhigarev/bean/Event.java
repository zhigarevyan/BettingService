package by.zhigarev.bean;

import java.util.Date;
import java.util.Objects;

public class Event {
    private int id;
    private Participant firstParticipant;
    private Participant secondParticipant;
    private String location;
    private String info;
    private Date startDateTime;
    private EventStatus status;

    public Event(int id, Participant firstParticipant, Participant secondParticipant, String location, String info, Date startDateTime, EventStatus status) {
        this.id = id;
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
        this.location = location;
        this.info = info;
        this.startDateTime = startDateTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Participant getFirstParticipant() {
        return firstParticipant;
    }

    public void setFirstParticipant(Participant firstParticipant) {
        this.firstParticipant = firstParticipant;
    }

    public Participant getSecondParticipant() {
        return secondParticipant;
    }

    public void setSecondParticipant(Participant secondParticipant) {
        this.secondParticipant = secondParticipant;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getId() == event.getId() &&
                getFirstParticipant().equals(event.getFirstParticipant()) &&
                getSecondParticipant().equals(event.getSecondParticipant()) &&
                getLocation().equals(event.getLocation()) &&
                getInfo().equals(event.getInfo()) &&
                getStartDateTime().equals(event.getStartDateTime()) &&
                getStatus().equals(event.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstParticipant(), getSecondParticipant(), getLocation(), getInfo(), getStartDateTime(), getStatus());
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", firstParticipant=" + firstParticipant +
                ", secondParticipant=" + secondParticipant +
                ", location='" + location + '\'' +
                ", info='" + info + '\'' +
                ", startDateTime=" + startDateTime +
                ", status=" + status +
                '}';
    }
}
