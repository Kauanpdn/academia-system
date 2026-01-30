package test;

import model.Pagamento;
import dao.PagamentoDao;

import java.time.LocalDate;

public class TestandoPagamentoDao {
    public static void main(String[] args) {
        Pagamento p = new Pagamento();
        p.setMatriculaId(1); // existente
        p.setValor(120.0);
        p.setFormaPagamento("PIX");
        p.setStatus("PENDENTE");
        p.setDataVencimento(LocalDate.now().plusDays(5));
        p.setDataPagamento(null);

        PagamentoDao dao = new PagamentoDao();
        dao.cadastrar(p);

        System.out.println("✅ Pagamento criado!");
        
    }
}
