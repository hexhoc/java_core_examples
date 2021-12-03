package composite.departments.impl;

import composite.departments.Department;

public class SalesDepartment implements Department {

    @Override
    public void doWork() {
        System.out.println("Make sales");
    }
}
