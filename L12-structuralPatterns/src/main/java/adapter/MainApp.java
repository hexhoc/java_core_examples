package adapter;

import adapter.service.Computer;

public class MainApp {
    public static void main(String[] args) {
        //We have computer and memory card
        //We want the computer to read this card, but it can only read through the USB interface
        //Then we use MemoryCardUsbAdapter like wrapper for our memory card
        MemoryCard memoryCard = new MemoryCard();
        MemoryCardUsbAdapter memoryCardUsbAdapter = new MemoryCardUsbAdapter(memoryCard);

        Computer computer = new Computer();
        computer.writeDataToUSB(memoryCardUsbAdapter, "HELLLLOOOOO");
        computer.readDataFromUSB(memoryCardUsbAdapter);

    }
}
