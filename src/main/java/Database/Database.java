package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author davif
 */
public abstract class Database {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String nomeServidor = "localhost:3306";
    private static final String nomeBanco = "escola";
    private static final String caminho = "jdbc:mysql://" + nomeServidor + "/" + nomeBanco;
    private static final String usuario = "root";
    private static final String senhaBanco = "DAVI890";
    private static Connection connection = null;

    public static void connect() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            connection = DriverManager.getConnection(caminho, usuario, senhaBanco);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
