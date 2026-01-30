package test;

import model.Matricula;
import dao.MatriculaDao;
import java.time.LocalDate;

public class TestandoMatriculaDao {
    public static void main(String[] args) {
        Matricula matricula = new Matricula();
        matricula.setAlunoId(1);
        matricula.setPlanoId(1);
        matricula.setDataInicio(LocalDate.now());
        matricula.setDataFim(LocalDate.now().plusMonths(1));
        matricula.setStatus("ATIVA");
        matricula.setValorContratado(99.99);

        MatriculaDao matriculaDao = new MatriculaDao();
        matriculaDao.cadastrar(matricula);

        System.out.println("✅ Matrícula criada!");
    }
}
