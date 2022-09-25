package adapter;

public class MemoryCard {
    private String data;

    public String readFromMemory() {
        return data;
    }

    public void writeToMemory(String data) {
        this.data = data;
    }
}
