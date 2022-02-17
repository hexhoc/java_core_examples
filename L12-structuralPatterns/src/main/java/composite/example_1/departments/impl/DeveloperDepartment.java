package composite.example_1.departments.impl;

import composite.example_1.departments.Department;

public class DeveloperDepartment implements Department {
//    @Override
//    public void printName() {
//        System.out.println(DeveloperDepartment.class.getSimpleName());
//    }

    @Override
    public void doWork() {
        System.out.println("Writes code");
    }
}
