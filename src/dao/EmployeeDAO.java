package dao;

import dao.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    List<EmployeeDTO> getAllEmployees();
    void showAllEmployees();
    void addEmployee(String name, Double salary);
    EmployeeDTO getEmployee(int id);
    void updateEmployee(EmployeeDTO employee);
    void deleteEmployee(int id);
}
