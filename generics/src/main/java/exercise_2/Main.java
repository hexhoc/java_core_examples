package exercise_2;

import exercise_2.entity.Apple;
import exercise_2.entity.Box;
import exercise_2.entity.Orange;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 4; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());
        }

        appleBox2.add(new Apple());
        appleBox2.add(new Apple());

        System.out.println(appleBox.toString());
        System.out.println(appleBox.getWeight());

        System.out.println(orangeBox.toString());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));

        appleBox.addAll(appleBox2);

        System.out.println(appleBox.compare(orangeBox));

    }
}
