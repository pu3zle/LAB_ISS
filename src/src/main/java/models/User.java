package models;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "users")
public class User extends Entity<Integer> implements Serializable {
    private String username;
    private String password;
    private UserType type;

    public User() {
    }

    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String username, String password, Integer type)
    {
        this.username = username;
        this.password = password;
        this.type = UserType.values()[type];
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

    @Column(name="type")
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, type);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
