package composite.example_1.departments.impl;

import composite.example_1.departments.Department;

public class SalesDepartment implements Department {

    @Override
    public void doWork() {
        System.out.println("Make sales");
    }
}
