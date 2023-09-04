package dao;

import dao.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeDAO {

    List<EmployeeDTO> getAllEmployees();
    void showAllEmployees();
    void addEmployee(EmployeeDTO e);
    EmployeeDTO getEmployee(int id);
    void updateEmployee(EmployeeDTO employee);
    void deleteEmployee(int id);
}
