package decorator.datasources.impl;

import decorator.datasources.DataSource;

import java.util.Base64;

public class DataSourceEncryptionDecorator extends DataSourceDecorator {
    public DataSourceEncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    public String encode(String data) {
        byte[] result = Base64.getEncoder().encode(data.getBytes());
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }

        return new String(result);
    }

    public String decode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        Base64.getDecoder().decode(result);

        return new String(result);
    }
}
