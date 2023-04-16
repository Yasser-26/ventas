package db;
import java.sql.*;

public class database {
    private static Connection conexion;
    private static final String URL = "jdbc:mysql://aws-mysql.cqh1qzdyllnh.us-east-1.rds.amazonaws.com/Ventas";
    private static final String USER = "yasser";
    private static final String PASSWORD = "Juanchacal12";

    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
