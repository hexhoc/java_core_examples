package prototype;

public class MainApp {
    public static void main(String[] args) {
        Sheep mainSheep = new Sheep("Doli", 5);
        Sheep sheep1 = mainSheep.clone();
        Sheep sheep2 = mainSheep.clone();

        System.out.println(mainSheep.equals(sheep1));
        System.out.println(sheep1.equals(sheep2));
    }
}
