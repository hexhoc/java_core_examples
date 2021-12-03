package iterator;

public class MainApp {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.add("Hello");
        box.add("World");
        box.add("!");

        while(box.hasNext()) {
            System.out.println((box.next()));
        }
    }
}
