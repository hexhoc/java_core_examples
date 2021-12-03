package composite;

import composite.departments.Department;
import composite.departments.impl.DeveloperDepartment;
import composite.departments.impl.HeadDepartment;
import composite.departments.impl.HelpdeskDepartment;
import composite.departments.impl.SalesDepartment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        HeadDepartment headDepartment = new HeadDepartment();
        headDepartment.addDepartment(new SalesDepartment());
        headDepartment.addDepartment(new DeveloperDepartment());
        headDepartment.addDepartment(new HelpdeskDepartment());

        headDepartment.printDepartmentName();
        headDepartment.getToWork();
    }
}
