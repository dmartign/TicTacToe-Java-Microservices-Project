package cse551.tbd.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String username;
    private String password;
    @JsonIgnore
    private String token;

    // Disables ever serializing the password going out
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

}
