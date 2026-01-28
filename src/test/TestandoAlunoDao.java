package test;

import model.Aluno;
import dao.AlunoDao;

public class TestandoAlunoDao {
    public static void main(String[] args) {

        Aluno aluno = new Aluno();
        aluno.setNome("Maria Silva");
        aluno.setTelefone("11988887777");
        aluno.setEmail("maria@email.com");
        aluno.setDataNascimento(null); // ok se aceitar null
        aluno.setStatus("ATIVO");

        AlunoDao alunoDao = new AlunoDao();
        alunoDao.salvar(aluno);

        System.out.println("✅ Aluno inserido com sucesso!");
    }
}
