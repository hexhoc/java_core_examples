package io.entity;

import java.io.Serializable;

//implements Serializable is necessary condition for ObjectOutputStream
public class Person implements Serializable {

    private String name;
    private int age;
    private transient String hiddenField; //if field mark as transient that field will skip while serialization

    public Person(String name, int age, String hiddenField) {
        this.name = name;
        this.age = age;
        this.hiddenField = hiddenField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hiddenField='" + hiddenField + '\'' +
                '}';
    }
}
