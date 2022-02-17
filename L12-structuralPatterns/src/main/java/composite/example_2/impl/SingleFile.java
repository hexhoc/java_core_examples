package composite.example_2.impl;

import composite.example_2.File;

public class SingleFile implements File {

    private final String fileName;

    public SingleFile(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void print() {
        System.out.println(fileName);
    }
}