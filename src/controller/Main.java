package controller;

import config.TableSQL;
import dao.EmployeeDAO;

import dao.dto.EmployeeDTO;
import dao.impl.EmployeeDAOImpl;

/* By Agus */
public class Main {
    public static void main(String[] args) {
        TableSQL.createTableEmployees();
        EmployeeDAO employees = new EmployeeDAOImpl();
//        employees.addEmployee("Nico", 1000.00d);
        // This line is having an issue, it does not find the registry
//        System.out.println(employees.getEmployee(2));
//        employees.updateEmployee(new EmployeeDTO(2, "Juan", 3000));
//        employees.deleteEmployee(4);
        employees.showAllEmployees();
    }
}