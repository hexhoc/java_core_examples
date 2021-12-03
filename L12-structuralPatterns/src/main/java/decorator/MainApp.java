package decorator;

import decorator.datasources.DataSource;
import decorator.datasources.impl.DataSourceEncryptionDecorator;
import decorator.datasources.impl.FileDataSource;

public class MainApp {
    public static void main(String[] args) {

        DataSource encryptDataSource = new DataSourceEncryptionDecorator(new FileDataSource());
        encryptDataSource.writeData("HELLO");
        String data = encryptDataSource.readData();

//        FileDataSource fd = new FileDataSource();
//        fd.writeData("HELLO");
//        String data = fd.readData();

        System.out.println(data);
    }
}
