package sv.edu.udb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/discografia2";
    private static final String USUARIO = "root"; // Reemplazar con tu nombre de usuario
    private static final String CONTRASENA = ""; // Reemplazar con tu contraseña

    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante el proceso de conexión
            e.printStackTrace();
        }
        return conexion;
    }
}
