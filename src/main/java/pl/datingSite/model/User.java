package pl.datingSite.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import pl.datingSite.enums.*;
import pl.datingSite.model.messages.MessageBox;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username, password, email, name, surname, sex;
    private Date dateOfBirth;

    private ZodiacSign zodiacSign;
    private Profession profession;
    private MaritalStatus maritalStatus;
    private Education education;

    @Lob
    private byte[] avatar;

    private boolean enabled, fake;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch =  FetchType.LAZY)
    private Set<Notification> notifications;

    @OneToOne
    private City city;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private MessageBox messageBox;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private AppearanceAndCharacter appearanceAndCharacter;

    @JsonIgnore
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Friends friends;

    @ElementCollection
    private Set<String> interests;

    public User() {
        this.enabled = true;
        this.notifications = new HashSet<>();
        this.friends = new Friends();
        this.messageBox = new MessageBox();
    }

    public User(@NotNull String username, @NotNull String password, @NotNull String email, @NotNull String name, @NotNull String surname, @NotNull City city, @NotNull String sex, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.enabled = true;
        this.notifications = new HashSet<>();
        this.friends = new Friends();
        this.messageBox = new MessageBox();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public ZodiacSign getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(ZodiacSign zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public AppearanceAndCharacter getAppearanceAndCharacter() {
        return appearanceAndCharacter;
    }

    public void setAppearanceAndCharacter(AppearanceAndCharacter appearanceAndCharacter) {
        this.appearanceAndCharacter = appearanceAndCharacter;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public boolean isFake() {
        return fake;
    }

    public void setFake(boolean fake) {
        this.fake = fake;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", zodiacSign=" + zodiacSign +
                ", profession=" + profession +
                ", maritalStatus=" + maritalStatus +
                ", education=" + education +
                ", enabled=" + enabled +
                ", notifications=" + notifications +
                ", city=" + city +
                ", appearanceAndCharacter=" + appearanceAndCharacter +
                ", interests=" + interests +
                ", fake=" + fake +
                ", avatar=" + avatar +
                ", friends=" + friends +
                ", messageBox=" + messageBox +
                '}';
    }
}
