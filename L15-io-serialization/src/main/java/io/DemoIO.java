package io;

import io.entity.Person;

import java.io.*;

public class DemoIO {

    public static final String FILEPATH1 = "L15-io-serialization/src/main/resources/textfile.txt";
    public static final String FILEPATH2 = "L15-io-serialization/src/main/resources/objects.txt";

    public static void main(String[] args) {
        readFile();
        copyFile();
        writeObject();
        readObject();
    }

    public static void readFile() {
        try(var br = new BufferedReader(new FileReader(FILEPATH1))) {
            String line;

            while((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile() {
        try(var bufferedInputStream = new BufferedInputStream(new FileInputStream(FILEPATH1))) {
            try(var bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(FILEPATH1.replace("textfile.txt", "copy_textfile.txt")))) {

                byte[] buffer = new byte[2];
                int size;

                while((size = bufferedInputStream.read(buffer,0, buffer.length)) > 0) {
                    bufferedOutputStream.write(buffer, 0, size);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeObject() {
        try(var objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILEPATH2))) {
            Person person = new Person("VASYA", 20, "TEST");
            objectOutputStream.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readObject() {
        try(var objectInputStream = new ObjectInputStream(new FileInputStream(FILEPATH2))) {
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
