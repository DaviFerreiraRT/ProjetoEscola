package DAO;

import Models.Turma;
import Database.Database;
import Models.Professor;
import Models.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author davif
 */
public class TurmaDAO {

	public static Turma create(String nome, int professorId, int disciplinaId) {
		Connection connection = Database.getConnection();
		nome = nome.trim().toUpperCase();
		Professor professor = ProfessorDAO.findById(professorId);
		Disciplina disciplina = DisciplinaDAO.findById(disciplinaId);
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO TURMA(NOME,PROFESSORID,DISCIPLINAID) VALUES (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, nome);
			ps.setInt(2, professorId);
			ps.setInt(3, disciplinaId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return new Turma(id, nome, professor, disciplina);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
