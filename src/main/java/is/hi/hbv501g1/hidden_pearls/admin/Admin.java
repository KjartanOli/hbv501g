package is.hi.hbv501g1.hidden_pearls.admin;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    
    public long getId() {
        return id;
    }

    // þessa þarf mögulega ekki
    public void setId(long id) {
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

}