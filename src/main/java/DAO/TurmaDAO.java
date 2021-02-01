package DAO;

import Models.Turma;
import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author davif
 */
public class TurmaDAO {

    public static Turma create(String nome, int professorId, int disciplinaId) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(""
                    + "INSERT INTO TURMA(NOME,PROFESSORID,DISCIPLINAID VALUES"
                    + "(?,?,?)");
            ps.setString(1, nome);
            ps.setInt(2, professorId);
            ps.setInt(3, disciplinaId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
