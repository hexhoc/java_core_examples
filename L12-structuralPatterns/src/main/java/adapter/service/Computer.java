package adapter.service;

public class Computer {
    public void readDataFromUSB(USB usb) {
        System.out.println(usb.getData());
    }

    public void writeDataToUSB(USB usb, String data) {
        usb.setData(data);
    }
}
