package cloud.popples.springwithorm.spittr.domain;

import java.util.Date;
import java.util.Objects;

public class Spittle {
    private final Long id;
    private final String message;
    private final Date time;

    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
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
               && Objects.equals(other.time, this.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }
}
