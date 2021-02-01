package Principal;

import DAO.AlunoDAO;
import DAO.DisciplinaDAO;
import DAO.ProfessorDAO;
import Models.Aluno;
import Models.Professor;
import Models.Disciplina;
import javax.swing.JOptionPane;
import Database.Database;

/**
 *
 * @author davif
 */
public class Principal {

    public static void main(String[] args) {
        Database.connect();
        menuPrincipal();

        Database.disconnect();
    }

    public static void menuPrincipal() {
        String escolha = "0";

        do {
            escolha = JOptionPane.showInputDialog(""
                    + "1-Aluno\n"
                    + "2-Professor\n"
                    + "3-Disciplina\n");
            if (escolha.equals("1")) {
                menuAluno();
            } else if (escolha.equals("2")) {
                menuProfessor();
            } else if (escolha.equals("3")) {
                menuDisciplina();
            }

        } while (!escolha.equals("0"));
    }

    public static void menuAluno() {
        String escolha = "0";

        do {
            escolha = JOptionPane.showInputDialog(""
                    + "1-Cadastrar aluno\n"
                    + "2-Buscar aluno\n"
                    + "3-Editar dados do aluno\n"
                    + "4-Excluir aluno\n"
                    + "0-Voltar ao menu principal");
            if (escolha.equals("1")) {

                String nome = JOptionPane.showInputDialog("Digite seu nome");
                String sexo = JOptionPane.showInputDialog("Digite seu sexo");
                Aluno aluno = AlunoDAO.create(nome, sexo);
                if (aluno != null) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!\n"
                            + "Matricula: " + aluno.getMatricula() + "\n"
                            + "Nome: " + aluno.getNome() + "\n"
                            + "Sexo: " + aluno.getSexo());
                }

            } else if (escolha.equals("2")) {
                int matricula = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite a matricula do aluno que deseja buscar"));
                Aluno aluno = AlunoDAO.findById(matricula);
                if (aluno != null) {
                    JOptionPane.showMessageDialog(null, "Aluno encontrado!\n"
                            + "Matricula: " + aluno.getMatricula() + "\n"
                            + "Nome: " + aluno.getNome() + "\n"
                            + "Sexo: " + aluno.getSexo());
                }
            } else if (escolha.equals("3")) {
                int matricula = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite a matricula do aluno que deseja remover"));
                AlunoDAO.removeById(matricula);
            } else if (escolha.equals("4")) {
                int matricula = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite a matricula do aluno que deseja alterar"));
                String nome = JOptionPane.showInputDialog("Digite seu nome");
                String sexo = JOptionPane.showInputDialog("Digite seu sexo");
                AlunoDAO.update(matricula, nome, sexo);
            }

        } while (!escolha.equals("0"));
    }

    public static void menuProfessor() {
        String escolha = "0";

        do {
            escolha = JOptionPane.showInputDialog(""
                    + "1-Cadastrar professor\n"
                    + "2-Buscar professor\n"
                    + "3-Editar dados do professor\n"
                    + "4-Excluir professor\n"
                    + "0-Voltar ao menu principal");
            if (escolha.equals("1")) {
                String nome = JOptionPane.showInputDialog("Digite seu nome");
                String sexo = JOptionPane.showInputDialog("Digite seu sexo");
                Professor professor = ProfessorDAO.create(nome, sexo);
                if (professor != null) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!\n"
                            + "Matricula: " + professor.getId() + "\n"
                            + "Nome: " + professor.getNome() + "\n"
                            + "Sexo: " + professor.getSexo());
                }
            } else if (escolha.equals("2")) {
                int id = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite o ID do professor que deseja buscar"));
                Professor professor = ProfessorDAO.findById(id);
                if (professor != null) {
                    JOptionPane.showMessageDialog(null, "Aluno encontrado!\n"
                            + "Matricula: " + professor.getId() + "\n"
                            + "Nome: " + professor.getNome() + "\n"
                            + "Sexo: " + professor.getSexo());
                }
            } else if (escolha.equals("3")) {
                int id = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite a matricula do professor que deseja alterar"));
                String nome = JOptionPane.showInputDialog("Digite seu nome");
                String sexo = JOptionPane.showInputDialog("Digite seu sexo");
                ProfessorDAO.update(id, nome, sexo);
            } else if (escolha.equals("4")) {
                int id = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite o ID do professor que deseja remover"));
                ProfessorDAO.removeById(id);
            }
        } while (!escolha.equals("0"));
    }

    public static void menuDisciplina() {
        String escolha = "0";

        do {
            escolha = JOptionPane.showInputDialog(""
                    + "1-Cadastrar disciplina\n"
                    + "2-Buscar disciplina\n"
                    + "3-Editar dados do disciplina\n"
                    + "4-Excluir disciplina\n"
                    + "0-Voltar ao menu principal");
            if (escolha.equals("1")) {
                String nome = JOptionPane.showInputDialog("Digite nome da "
                        + "disciplina");
                int id = Integer.valueOf(JOptionPane.showInputDialog(
                        "Digite o id do professor da disciplina"));
                Disciplina disciplina = DisciplinaDAO.create(nome, id);
                if (disciplina != null) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!\n"
                            + "Codigo: " + disciplina.getId() + "\n"
                            + "Nome da disciplina: " + disciplina.getNome() + "\n"
                            + "Nome do professor: " + disciplina.getProfessor().getNome());
                }
            }

        } while (!escolha.equals("0"));
    }
}
