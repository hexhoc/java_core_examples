package composite.example_1;

import composite.example_1.departments.impl.DeveloperDepartment;
import composite.example_1.departments.impl.HeadDepartment;
import composite.example_1.departments.impl.HelpdeskDepartment;
import composite.example_1.departments.impl.SalesDepartment;

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
