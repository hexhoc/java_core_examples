package composite.departments.impl;

import composite.departments.Department;

public class HelpdeskDepartment implements Department {

    @Override
    public void doWork() {
        System.out.println("Repair printers");
    }
}
