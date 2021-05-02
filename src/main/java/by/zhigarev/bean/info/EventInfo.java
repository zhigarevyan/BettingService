package by.zhigarev.bean.info;

import java.util.Date;
import java.util.Objects;

public class EventInfo {
    private int id;
    private int firstParticipant;
    private int secondParticipant;
    private String location;
    private String info;
    private Date startDateTime;
    private int status;

    public EventInfo(int firstParticipant, int secondParticipant, String location, String info, Date startDateTime) {
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
        this.location = location;
        this.info = info;
        this.startDateTime = startDateTime;
    }

    public EventInfo(int id, int firstParticipant, int secondParticipant, String location, String info, Date startDateTime, int status) {
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

    public int getFirstParticipant() {
        return firstParticipant;
    }

    public void setFirstParticipant(int firstParticipant) {
        this.firstParticipant = firstParticipant;
    }

    public int getSecondParticipant() {
        return secondParticipant;
    }

    public void setSecondParticipant(int secondParticipant) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInfo eventInfo = (EventInfo) o;
        return getId() == eventInfo.getId() &&
                getFirstParticipant() == eventInfo.getFirstParticipant() &&
                getSecondParticipant() == eventInfo.getSecondParticipant() &&
                Objects.equals(getLocation(), eventInfo.getLocation()) &&
                Objects.equals(getInfo(), eventInfo.getInfo()) &&
                Objects.equals(getStartDateTime(), eventInfo.getStartDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstParticipant(), getSecondParticipant(), getLocation(), getInfo(), getStartDateTime());
    }
}
