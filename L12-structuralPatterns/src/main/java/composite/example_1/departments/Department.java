package composite.example_1.departments;

public interface Department {
    default void printName() {
        System.out.println(getClass().getSimpleName());
    };
    void doWork();
}
