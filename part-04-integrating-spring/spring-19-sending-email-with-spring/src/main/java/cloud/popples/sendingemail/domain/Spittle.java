package cloud.popples.sendingemail.domain;

import java.util.Date;
import java.util.Objects;

public class Spittle {
    private Long id;
    private String message;
    private Date time;
    private Double latitude;
    private Double longitude;
    private Spitter spitter;

    public Spittle() {}

    public Spittle(Long id, String message, Date time, Double latitude, Double longitude, Spitter spitter) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.spitter = spitter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spittle spittle = (Spittle) o;
        return Objects.equals(id, spittle.id) && Objects.equals(message, spittle.message) && Objects.equals(time, spittle.time) && Objects.equals(spitter, spittle.spitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, time);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Spittle{");
        sb.append("id=").append(id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", time=").append(time);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", spitter=").append(spitter);
        sb.append('}');
        return sb.toString();
    }
}
