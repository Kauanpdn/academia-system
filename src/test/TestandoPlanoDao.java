package test;

import model.Plano;
import dao.PlanoDao;

public class TestandoPlanoDao {
    public static void main(String[] args) {
        Plano plano = new Plano();
        plano.setNome("Básico");
        plano.setValor(99.99);
        plano.setDuracaoMeses(1);
        plano.setDescricao("Mensal");

        PlanoDao planoDao = new PlanoDao();
        planoDao.salvar(plano);

        System.out.println("✅ Plano inserido com sucesso!");
    }
}
