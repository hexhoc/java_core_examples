package facade;

import facade.datasources.impl.FileDataSource;

public class MainApp {
    public static void main(String[] args) {
        FileDataSource fd = new FileDataSource();
        fd.writeData("HELLO");
        System.out.println(fd.readData());
    }
}
