package pl.datingSite.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String email, activationCode;

    public ActivationCode() {
    }

    public ActivationCode(@NotNull String email, @NotNull String activationCode) {
        this.email = email;
        this.activationCode = activationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationCode that = (ActivationCode) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(activationCode, that.activationCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, activationCode);
    }

    @Override
    public String toString() {
        return "ActivationCode{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }
}
