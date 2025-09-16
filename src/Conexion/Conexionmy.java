
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bryan
 */
public class Conexionmy {

    public static Connection Conectar() {

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/prestamos_1?useSSL=false&allowPublicKeyRetrieval=true", "root","Guitar3bry");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local: " + e);
        }
        return null;
    }

}
