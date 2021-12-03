package composite.departments.impl;

import composite.departments.Department;

import java.util.ArrayList;
import java.util.List;

public class HeadDepartment {
    private List<Department> childDepartments = new ArrayList<>();

    public HeadDepartment() { }

    public HeadDepartment(ArrayList<Department> departments) {
        this.childDepartments = departments;
    }

    public void printDepartmentName() {
        childDepartments.forEach(Department::printName);
    }

    public void getToWork() {
        childDepartments.forEach(Department::doWork);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}
