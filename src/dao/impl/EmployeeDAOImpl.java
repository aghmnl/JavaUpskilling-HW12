package dao.impl;

import dao.EmployeeDAO;
import dao.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.JDBCConfig.getDBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

    // Para transformar un ResultSet en una lista de EmployeeDTO
     List<EmployeeDTO> resultSetToList(ResultSet allEmployees) throws SQLException {
         List<EmployeeDTO> newListEmployees = new ArrayList<>();
         while(allEmployees.next()) {
             int id = allEmployees.getInt("id");
             String nombre = allEmployees.getString("nombre");
             double salario = allEmployees.getDouble("salario");
             newListEmployees.add(new EmployeeDTO(id, nombre, salario));
         }
         return newListEmployees;
     }

    // Para transformar un ResultSet en un EmployeeDTO
    EmployeeDTO resultSetToEmployeeDTO(ResultSet employee) throws SQLException {
        return new EmployeeDTO(employee.getInt("id"), employee.getString("nombre"), employee.getDouble("salario"));
    }

    // Para obtener todos los empleados
    @Override
    public List<EmployeeDTO> getAllEmployees() {

        try {
            // Establecer la conexión
            Connection connection = getDBConnection();

            // Realizar operaciones en la base de datos
            Statement statement = connection.createStatement();

            // Sentencia SQL para leer todos los empleados
            String getAllEmployeesSQL = "SELECT * FROM gestion_empleados.Empleados;";

            // Ejecuta la consulta
            ResultSet allEmployees = statement.executeQuery(getAllEmployeesSQL);

            // Devuelve el resultado
            return resultSetToList(allEmployees);

            // Cerrar la conexión
//            statement.close();
//            connection.close();

        } catch (SQLException e) {
            System.out.println("No se pudieron encontrar todos los empleados");
            throw new RuntimeException(e);
        }

    }

    // Para mostrar todos los empleados por consola
    @Override
    public void showAllEmployees() {
        List<EmployeeDTO> allEmployees = getAllEmployees();
        for (EmployeeDTO employee : allEmployees) {
            System.out.println(employee);
        }
    }

    // Para agregar un nuevo empleado en la tabla Empleados
    @Override
    public void addEmployee(String name, Double salary) {
        try {
            // Establecer la conexión
            Connection connection = getDBConnection();

            // Sentencia SQL para agregar un empleado
            String addEmployeeSQL = "INSERT INTO gestion_empleados.Empleados (nombre, salario) VALUES (?, ?);";

            // Realizar operaciones en la base de datos
            PreparedStatement statement = connection.prepareStatement(addEmployeeSQL);

            // Establecer los valores en el PreparedStatement
            statement.setString(1, name);
            statement.setDouble(2, salary);

            // Ejecutar la inserción
            statement.executeUpdate();

            // Cerrar la conexión
//            statement.close();
//            connection.close();

        } catch (SQLException e) {
            System.out.println("El registro no pudo ser agregado");
            throw new RuntimeException(e);
        }
    }

    // Para obtener un empleado según su id
    @Override
    public EmployeeDTO getEmployee(int id) {

        try {
            // Establecer la conexión
            Connection connection = getDBConnection();

            // Sentencia SQL para obtener un empleado según su id
            String getEmployeeSQL = "SELECT * FROM gestion_empleados.Empleados WHERE id = ?";

            // Realizar operaciones en la base de datos
            PreparedStatement statement = connection.prepareStatement(getEmployeeSQL);

            // Establecer los valores en el PreparedStatement
            statement.setInt(1, id);

            // Ejecuta la consulta
            ResultSet employee = statement.executeQuery();

            // Cerrar la conexión
//            statement.close();
//            connection.close();

            // Ejecuta la consulta y devuelve el resultado
            return resultSetToEmployeeDTO(employee);

        } catch (SQLException e) {
            System.out.println("No se encontró el registro");
            throw new RuntimeException(e);
        }
    }


    // Para actualizar un empleado según su ID
    @Override
    public void updateEmployee(EmployeeDTO employee) {

        try {
            // Establecer la conexión
            Connection connection = getDBConnection();

            // Sentencia SQL para actualizar un empleado según su id
            String updateEmployeeSQL = "UPDATE gestion_empleados.Empleados SET nombre = ?, salario = ? WHERE id = ?";

            // Realizar operaciones en la base de datos
            PreparedStatement statement = connection.prepareStatement(updateEmployeeSQL);

            // Establecer los valores en el PreparedStatement
            statement.setString(1, employee.getName());
            statement.setDouble(2, employee.getSalary());
            statement.setInt(3, employee.getId());

            // Ejecutar la actualización
            int numberOfRows = statement.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (numberOfRows > 0) {
                System.out.println("El registro se actualizó exitosamente.");
            } else {
                System.out.println("No se encontró el registro especificado.");
            }

            // Cerrar la conexión
//            statement.close();
//            connection.close();

        } catch (SQLException e) {
            System.out.println("El registro no pudo ser actualizado");
            throw new RuntimeException(e);
        }
    }

    // Para borrar un empleado según su ID
    @Override
    public void deleteEmployee(int id) {

        try {
            // Establecer la conexión
            Connection connection = getDBConnection();

            // Sentencia SQL para actualizar un empleado según su id
            String deleteEmployeeSQL = "DELETE FROM gestion_empleados.Empleados WHERE id = ?";

            // Realizar operaciones en la base de datos
            PreparedStatement statement = connection.prepareStatement(deleteEmployeeSQL);

            // Establecer los valores en el PreparedStatement
            statement.setInt(1, id);

            // Ejecutar la eliminación
            int numberOfRows = statement.executeUpdate();

            // Verificar si la eliminación fue exitosa
            if (numberOfRows > 0) {
                System.out.println("El registro se eliminó exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el registro.");
            }

            // Cerrar la conexión
//            statement.close();
//            connection.close();

        } catch (SQLException e) {
            System.out.println("El registro no pudo ser eliminado");
            throw new RuntimeException(e);
        }
    }
}