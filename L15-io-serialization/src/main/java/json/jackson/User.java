package json.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"password"}) //exclude password property from serialization
public class User {

    @JsonProperty("UID") //change name of property
    private String login;

    private String password;


    @JsonCreator //Constructor for json
    public User(@JsonProperty("UID") String login) {
        this.login = login;
    }

    public User() { }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
