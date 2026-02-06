package service;

import dao.AlunoDao;
import dao.MatriculaDao;
import dao.PlanoDao;
import model.Matricula;

import java.time.LocalDate;

public class MatriculaService {

    private MatriculaDao matriculaDao = new MatriculaDao();
    private AlunoDao alunoDao = new AlunoDao();
    private PlanoDao planoDao = new PlanoDao();

    public void criarMatricula(Matricula matricula) {

        // 1. Aluno existe?
        if (alunoDao.buscarPorId(matricula.getAlunoId()) == null) {
            throw new RuntimeException("Aluno não encontrado!");
        }

        // 2. Plano existe?
        if (planoDao.buscarPorId(matricula.getPlanoId()) == null) {
            throw new RuntimeException("Plano não encontrado!");
        }

        // 3. Evitar matrícula duplicada ativa
        boolean existeAtiva = matriculaDao.existeMatriculaAtiva(matricula.getAlunoId());
        if (existeAtiva) {
            throw new RuntimeException("Aluno já possui matrícula ativa!");
        }

        // 4. Regras automáticas
        matricula.setDataInicio(LocalDate.now());
        matricula.setStatus("ATIVA");

        matriculaDao.cadastrar(matricula);
    }

    public void cancelarMatricula(int matriculaId) {

        Matricula matricula = matriculaDao.buscarPorId(matriculaId);

        if (matricula == null) {
            throw new RuntimeException("Matrícula não encontrada!");
        }

        matricula.setStatus("CANCELADA");
        matriculaDao.atualizar(matricula);
    }
}
