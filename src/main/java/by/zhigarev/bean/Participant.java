package by.zhigarev.bean;

import java.util.Objects;

public class Participant {
    private int id;
    private String name;
    private String surName;
    private String info;
    private String image;

    public Participant(int id, String name, String surName, String info, String image) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.info = info;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return getId() == that.getId() &&
                getName().equals(that.getName()) &&
                getSurName().equals(that.getSurName()) &&
                getInfo().equals(that.getInfo()) &&
                getImage().equals(that.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurName(), getInfo(), getImage());
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
