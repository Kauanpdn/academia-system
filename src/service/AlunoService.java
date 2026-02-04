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

    public void cadastrarAluno(Aluno aluno) {

        validarAluno(aluno);

    }

    private void validarAluno(Aluno aluno) {

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

        if (aluno.getTelefone() == null || aluno.getTelefone().isBlank()) {
            throw new RuntimeException("Telefone é obrigatório");
        }

        if (aluno.getDataNascimento() == null) {
            throw new RuntimeException("Data de nascimento é obrigatória");
        }

        if (aluno.getDataNascimento().isAfter(LocalDate.now())) {
            throw new RuntimeException("Data de nascimento não pode ser futura");
        }

        int idade = calcularIdade(aluno.getDataNascimento());

        if (idade < 14) {
            throw new RuntimeException("Aluno deve ter no mínimo 14 anos");
        }

    }

    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
