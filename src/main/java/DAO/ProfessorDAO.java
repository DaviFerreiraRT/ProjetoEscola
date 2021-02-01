package DAO;

import Models.Professor;
import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author davif
 */
public class ProfessorDAO {

    public static Professor create(String nome, String sexo) {
        Connection connection = Database.getConnection();
        nome = nome.trim().toUpperCase();
        sexo = sexo.trim().toUpperCase();
        try {
            PreparedStatement ps = connection.prepareStatement(""
                    + "INSERT INTO PROFESSOR(NOME,SEXO) VALUES (?,?);",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            ps.setString(2, sexo);
            if (sexo.equals("M") || sexo.equals("F")) {
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();

                int id = rs.getInt(1);
                return new Professor(id, nome, sexo);
            } else {
                JOptionPane.showMessageDialog(null, "Sexo deve ser M ou F!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static Professor findById(int id) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(""
                    + "SELECT * FROM PROFESSOR WHERE ID = ?");

            ps.setInt(1, id);

            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                return new Professor(id, nome, sexo);

            } else {
                JOptionPane.showMessageDialog(null, ""
                        + "Não foi encontrado nenhum professor com este ID!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void removeById(int id) {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(""
                    + "DELETE FROM PROFESSOR WHERE ID=?");
            ps.setInt(1, id);
            int exe=ps.executeUpdate();
            if(exe>0){
                JOptionPane.showMessageDialog(null, "O professor foi removido");
            }else{
                JOptionPane.showMessageDialog(null,""
                        + "O professor com este ID não foi encontrado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    public static void update(int id, String nome, String sexo) {
        Connection connection = Database.getConnection();
        nome = nome.trim().toUpperCase();
        sexo = sexo.trim().toUpperCase();
        String sql = "UPDATE PROFESSOR SET";
        boolean isFirst = true;

        if (!nome.equals("")) {
            isFirst = false;
            sql += " NOME = '" + nome + "'";
        }
        if (!sexo.equals("")) {
            if (!isFirst) {
                sql += ",";
            } else {
                isFirst = false;
            }
            sql += " SEXO = '" + sexo + "'";
        }

        sql += " WHERE ID = " + id;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int exec = ps.executeUpdate();
            if (exec > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram alterados!");
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nenhum professor"
                        + " com este ID");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
