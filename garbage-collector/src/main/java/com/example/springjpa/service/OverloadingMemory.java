package com.example.garbagecollector.service;

import java.util.ArrayList;
import java.util.List;

import com.example.garbagecollector.model.Person;
import org.springframework.stereotype.Service;

@Service
public class OverloadingMemory {
    private static List<Person> staticList = new ArrayList<>();

    public void generateNumberOfObject(int count) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Person("first name", "last name"));
        }
    }

    public void generateNumberOfObjectWithMemoryLeak(int count) {
        for (int i = staticList.size(); i < staticList.size() + count; i++) {
            staticList.add(new Person("first name", "last name"));
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
