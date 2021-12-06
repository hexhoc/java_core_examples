package json.xjson;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JavaxJsonDemo {

    public static final String FILEPATH1 = "L15-io-serialization/src/main/resources/jsondata.json";
    public static final String FILEPATH2 = "L15-io-serialization/src/main/resources/jsondata2.json";

    public static void main(String[] args) {
        navigateTree(create());
        readJsonFromFile();
        writeJsonToFile();
    }

    public static JsonObject create() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("firstName", "Duke")
                .add("age", 29)
                .add("streetAddress", "100 Internet st")
                .add("phoneNumber",
                        Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("type", "phone")
                                        .add("number", "123123")
                                )).build();

        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    public static void navigateTree(JsonValue tree) {

        switch(tree.getValueType()) {
            case OBJECT -> {
                System.out.println("OBJECT");
                var jsonObject = (JsonObject) tree;
                for (var entry : jsonObject.entrySet()) {
                    navigateTree(jsonObject.get(entry.getKey()));
                }
            }
            case ARRAY -> {
                System.out.println("ARRAY");
                JsonArray jsonArray = (JsonArray) tree;
                for (JsonValue val : jsonArray) {
                    navigateTree(val);
                }
            }
            case STRING -> {
                JsonString str = (JsonString) tree;
                System.out.println(str.getString());
            }
            case NUMBER -> {
                JsonNumber num = (JsonNumber) tree;
                System.out.println(num.toString());
            }
            case TRUE -> System.out.println("TRUE");
            case FALSE -> System.out.println("FALSE");
            case NULL -> System.out.println(tree.getValueType().toString());
        }
    }

    public static void readJsonFromFile() {
        try(var jsonReader = Json.createReader(new FileReader(FILEPATH1))) {
            JsonStructure jsonStructure = jsonReader.read();
            System.out.println(jsonStructure);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeJsonToFile() {
        try(var jsonWriter = Json.createWriter(new FileWriter(FILEPATH2))) {
            jsonWriter.write(create());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

