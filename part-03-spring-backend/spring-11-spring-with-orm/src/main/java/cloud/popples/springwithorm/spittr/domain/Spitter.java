package cloud.popples.springwithorm.spittr.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Spitter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "{username.size}")
    private String username;

    @NotNull
    @Size(min = 5, max = 25, message = "{password.size}")
    private String password;

    @NotNull
    @Size(min = 2, max = 30, message = "{firstName.size}")
    private String fullName;

    @NotNull(message = "{email.valid}")
    private String email;

//    @Column(name = "updateByEmail", columnDefinition = "tinyint NOT NULL")
    private Boolean updateByEmail;

    public Spitter() {
        this(null, null, null, null, null);
    }

    public Spitter(String username, String password, String fullName, String email, Boolean updateByEmail) {
        this(null, username, password, fullName, email, updateByEmail);
    }

    public Spitter(Long id, String username, String password, String fullName, String email, Boolean updateByEmail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.updateByEmail = updateByEmail;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Boolean getUpdateByEmail() {
        return updateByEmail;
    }

    public void setUpdateByEmail(Boolean updateByEmail) {
        this.updateByEmail = updateByEmail;
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spitter spitter = (Spitter) o;
        return Objects.equals(username, spitter.username)
                && Objects.equals(password, spitter.password)
                && Objects.equals(fullName, spitter.fullName)
                && Objects.equals(email, spitter.email)
                && Objects.equals(updateByEmail, spitter.updateByEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, fullName, email, updateByEmail);
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", updateByEmail=" + updateByEmail +
                '}';
    }
}
