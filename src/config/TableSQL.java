package config;

import java.sql.*;

import static config.JDBCConfig.getDBConnection;

public class TableSQL {


    // Creando tabla en base de datos
    public static void createTableEmployees() {
        try {
            // Establecer la conexión
            Connection connection = getDBConnection();

            // Realizar operaciones en la base de datos
            Statement statement = connection.createStatement();

            // Creando un Schema gestion_empleados
            String createSchema = "CREATE SCHEMA IF NOT EXISTS gestion_empleados;";
            statement.executeUpdate(createSchema);

            // Creando una tabla Empleados
            String createTableEmployees = "CREATE TABLE IF NOT EXISTS gestion_empleados.Empleados (id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nombre VARCHAR(250) NOT NULL, salario DECIMAL);";
            statement.executeUpdate(createTableEmployees);

            // Cerrar la conexión
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
