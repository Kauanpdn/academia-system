package service;

import dao.MatriculaDao;
import dao.PagamentoDao;
import model.Pagamento;

import java.time.LocalDate;

public class PagamentoService {

    private PagamentoDao pagamentoDao = new PagamentoDao();
    private MatriculaDao matriculaDao = new MatriculaDao();

    public void gerarPagamento(Pagamento pagamento) {

        // 1. Matrícula existe?
        if (matriculaDao.buscarPorId(pagamento.getMatriculaId()) == null) {
            throw new RuntimeException("Matrícula não encontrada!");
        }

        // 2. Valor válido
        if (pagamento.getValor() <= 0) {
            throw new RuntimeException("Valor do pagamento inválido!");
        }

        // 3. Data de vencimento obrigatória
        if (pagamento.getDataVencimento() == null) {
            throw new RuntimeException("Data de vencimento obrigatória!");
        }

        // 4. Regras automáticas
        pagamento.setStatus("PENDENTE");
        pagamento.setDataPagamento(null);

        pagamentoDao.cadastrar(pagamento);
    }

    public void pagar(int pagamentoId) {

        Pagamento pagamento = pagamentoDao.buscarPorId(pagamentoId);

        if (pagamento == null) {
            throw new RuntimeException("Pagamento não encontrado!");
        }

        if ("PAGO".equals(pagamento.getStatus())) {
            throw new RuntimeException("Pagamento já foi realizado!");
        }

        pagamento.setStatus("PAGO");
        pagamento.setDataPagamento(LocalDate.now());

        pagamentoDao.atualizar(pagamento);
    }
}
