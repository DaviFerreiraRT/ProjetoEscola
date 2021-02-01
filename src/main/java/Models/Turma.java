package Models;

/**
 *
 * @author davif
 */
public class Turma {
 private int id;
 private String nome;
 private Professor professor; 
 private Disciplina disciplina;

    public Turma(int id, String nome, Professor professor, Disciplina disciplina) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.disciplina = disciplina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
}
