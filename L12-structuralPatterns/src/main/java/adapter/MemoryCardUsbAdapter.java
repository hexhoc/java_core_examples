package adapter;

import adapter.service.USB;

public class MemoryCardUsbAdapter implements USB {

    private MemoryCard memoryCard;

    public MemoryCardUsbAdapter(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    @Override
    public String getData() {
        return memoryCard.readfromMemory();
    }

    @Override
    public void setData(String data) {
        memoryCard.writeToMemory(data);
    }
}
