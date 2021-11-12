package exercise_2.entity;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private List<T> fruitsList= new ArrayList<>();

    public void add(T element) {
        fruitsList.add(element);
    }

    public void addAll(Box<T> otherBox) {
        for (int i = 0; i < otherBox.size(); i++) {
            add(otherBox.get(i));
        }
        otherBox.removeAll();
    }

    public T get(int index) {
        return fruitsList.get(index);
    }

    public void remove(int index) {
        fruitsList.remove(index);
    }

    public void removeAll() {
        fruitsList.removeAll(fruitsList);
    }

    public int size() {
        return fruitsList.size();
    }
    public boolean compare(Box<? extends Fruit> otherBox) {
        return Math.abs(getWeight() - otherBox.getWeight()) <= 0.00001;
    }

    public double getWeight() {
        if (size() == 0) {
            return 0;
        } else {
            return get(0).getWeight() * size();
        }
    }
}
