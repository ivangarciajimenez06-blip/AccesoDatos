package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexión {

    private static final String URL = "jdbc:mysql://localhost:3306/INSTITUTO";
    private static final String USER = "root";
    private static final String PASSWORD = "9=*b3KTy";

    // Opcional: cargar el driver explícitamente (a veces no hace falta)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("No se ha podido cargar el driver de MySQL");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
