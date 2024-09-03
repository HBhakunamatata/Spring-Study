package cloud.popples.securingmethods.spittr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Spittle implements Serializable {

    private Long id;
    private Spitter spitter;
    private String message;

    private Date postedTime;

    public Spittle() {}

    public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.postedTime = postedTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == this) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (otherObject.getClass() != this.getClass()) {
            return false;
        }

        Spittle other = (Spittle) otherObject;

        return Objects.equals(other.id, this.id)
               && Objects.equals(other.postedTime, this.postedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postedTime);
    }
}
