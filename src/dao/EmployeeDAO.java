package dao;

import dao.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeDAO {

    List<EmployeeDTO> getAllEmployee();
    void mostrarTodos();
    void addEmployee(EmployeeDTO e);
    EmployeeDAO getEmployee(int id);
    void updateEmployee(int id, EmployeeDTO usuario);
    void deleteEmployee(int id);
}
