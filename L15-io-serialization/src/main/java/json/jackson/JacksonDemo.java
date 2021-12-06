package json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonDemo {
    public static final String FILEPATH = "L15-io-serialization/src/main/resources/jackson/jsonUser.json";

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        //CONVERT TO JSON
        mapper.writeValue(new File(FILEPATH), new User("newUser", "123123"));

        //CONVERT FROM JSON
        User user1 = mapper.readValue(new File(FILEPATH), User.class);
        System.out.println(user1);

        //SHOW LIKE A STRING
        String jsonValue = mapper.writeValueAsString(new User("newUser", "123123"));
        System.out.println(jsonValue);

        //READ FROM STRING
        User user2 = mapper.readValue(jsonValue, User.class);
        System.out.println(user2);
    }

}
