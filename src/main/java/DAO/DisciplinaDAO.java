package DAO;

import Database.Database;
import Models.Disciplina;
import Models.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author davif
 */
public class DisciplinaDAO {

    public static Disciplina create(String nome, int professorId) {
        Connection connection = Database.getConnection();
        nome = nome.trim().toUpperCase();

        try {
            Professor professor = ProfessorDAO.findById(professorId);
            if (professor != null) {
                PreparedStatement ps = connection.prepareStatement(
                        "" + "INSERT INTO DISCIPLINAS(NOME,PROFESSORID) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, nome);
                ps.setInt(2, professorId);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                return new Disciplina(id, nome, professor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static Disciplina findById(int id) {
        Connection connection = Database.getConnection();
        Professor professor = ProfessorDAO.findById(id);
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DISCIPLINAS WHERE ID = ?");

            ps.setInt(1, id);

            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                String nome = rs.getString("nome");

                return new Disciplina(id, nome, professor);

            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma disciplina com este ID foi encontrada!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void removeById(int disciplinaId) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("" + "DELETE FROM DISCIPLINAS WHERE ID = ?");
            ps.setInt(1, disciplinaId);
            int exec = ps.executeUpdate();
            if (exec > 0) {
                JOptionPane.showMessageDialog(null, "A disciplina foi removida!");
            } else {
                JOptionPane.showMessageDialog(null, "" + "O professor com este ID não foi encontrado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
