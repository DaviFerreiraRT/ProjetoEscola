package DAO;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Models.Aluno;

/**
 *
 * @author davif
 */
public abstract class AlunoDAO {

	public static Aluno create(String nome, String sexo) {
		Connection connection = Database.getConnection();

		try {
			nome = nome.trim().toUpperCase();
			sexo = sexo.trim().toUpperCase();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO ALUNOS(NOME,SEXO) VALUE(?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, nome);
			ps.setString(2, sexo);

			if (sexo.equals("M") || sexo.equals("F")) {

				ps.execute();

				ResultSet rs = ps.getGeneratedKeys();
				rs.next();

				int matricula = rs.getInt(1);
				return new Aluno(matricula, nome, sexo);
			} else {
				JOptionPane.showMessageDialog(null, "Sexo deve ser M ou F !");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public static Aluno findById(int matricula) {
		Connection connection = Database.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("" + "SELECT * FROM ALUNOS WHERE MATRICULA = ?");

			ps.setInt(1, matricula);

			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				String nome = rs.getString("nome");
				String sexo = rs.getString("sexo");
				return new Aluno(matricula, nome, sexo);

			} else {
				JOptionPane.showMessageDialog(null, "" + "Não foi encontrado nenhum aluno com esta matricula");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		return null;

	}

	public static void removeById(int matricula) {
		Connection connection = Database.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUNOS" + " WHERE MATRICULA = ?");
			ps.setInt(1, matricula);

			int exec = ps.executeUpdate();
			if (exec > 0) {
				JOptionPane.showMessageDialog(null, "O aluno foi removido!");
			} else {
				JOptionPane.showMessageDialog(null, "O aluno não foi encontrado!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}

	public static void update(int matricula, String nome, String sexo) {
		Connection connection = Database.getConnection();
		nome = nome.trim().toUpperCase();
		sexo = sexo.trim().toUpperCase();
		String sql = "UPDATE ALUNOS SET";
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

		sql += " WHERE MATRICULA = " + matricula;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			int exec = ps.executeUpdate();
			if (exec > 0) {
				JOptionPane.showMessageDialog(null, "Os dados foram alterados!");
			} else {
				JOptionPane.showMessageDialog(null, "Não existe nenhum aluno" + " com esta matricula");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
}
