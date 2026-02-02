package service;

import model.Aluno;
import dao.AlunoDao;

import java.time.LocalDate;
import java.time.Period;

public class AlunoService {
    
    private final AlunoDao alunoDao;

    public AlunoService() {
        this.alunoDao = new AlunoDao();
    }

    public void cadastrarAluno(Aluno aluno){

        validarAluno(aluno);

    }


    private void validarAluno(Aluno aluno){
            
            if (aluno == null) {
                throw new RuntimeException("Aluno não pode ser");
            }

            if (aluno.getNome() == null || aluno.getNome().isBlank()) {
                throw new RuntimeException("O nome é obrigatório");
            }

            if (aluno.getNome().length() < 3) {
                throw new RuntimeException("Nome deve ter no minímo 3 caracteres");
            }

            if (aluno.getEmail() == null || aluno.getEmail().isBlank()) {
                throw new RuntimeException("Email deve ser obrigatório");
            }

            if (aluno.getEmail().contains("@")) {
                throw new RuntimeException("Email inválido");
            }
            
        }

}
