package json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.entity.Person;

public class GsonDemo {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Person person = new Person("JOHN", 30, "HIDDEN");

        //CONVERT TO JSON
        String jsonString = gson.toJson(person);
        System.out.println(jsonString);

        //CONVERT FROM JSON
        Person objectFromJson = gson.fromJson(jsonString, Person.class);
        System.out.println(objectFromJson);

    }
}
