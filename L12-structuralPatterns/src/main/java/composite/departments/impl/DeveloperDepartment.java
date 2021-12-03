package composite.departments.impl;

import composite.departments.Department;

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
