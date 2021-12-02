package adapter;

public class MemoryCard {
    private String data;

    public String readfromMemory() {
        return data;
    }

    public void writeToMemory(String data) {
        this.data = data;
    }
}
