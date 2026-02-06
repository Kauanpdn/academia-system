package service;

import dao.PlanoDao;
import model.Plano;

import java.util.List;

public class PlanoService {

    private PlanoDao planoDao;

    public PlanoService() {
        this.planoDao = new PlanoDao();
    }

    // =========================
    // CRIAR PLANO
    // =========================
    public void criarPlano(Plano plano) {

        if (plano == null) {
            throw new IllegalArgumentException("Plano não pode ser nulo.");
        }

        if (plano.getNome() == null || plano.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do plano é obrigatório.");
        }

        if (plano.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do plano deve ser maior que zero.");
        }

        if (plano.getDuracaoMeses() <= 0) {
            throw new IllegalArgumentException("Duração do plano deve ser maior que zero.");
        }

        planoDao.salvar(plano);
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Plano buscarPorId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Plano plano = planoDao.buscarPorId(id);

        if (plano == null) {
            throw new RuntimeException("Plano não encontrado.");
        }

        return plano;
    }

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Plano> listarTodos() {
        return planoDao.listarTodos();
    }

    // =========================
    // ATUALIZAR PLANO
    // =========================
    public void atualizarPlano(Plano plano) {

        if (plano == null) {
            throw new IllegalArgumentException("Plano não pode ser nulo.");
        }

        if (plano.getId() <= 0) {
            throw new IllegalArgumentException("ID do plano é obrigatório.");
        }

        if (plano.getNome() == null || plano.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do plano é obrigatório.");
        }

        if (plano.getValor() <= 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }

        if (plano.getDuracaoMeses() <= 0) {
            throw new IllegalArgumentException("Duração inválida.");
        }

        planoDao.atualizar(plano);
    }

    // =========================
    // DELETAR PLANO
    // =========================
    public void deletarPlano(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        planoDao.deletar(id);
    }
}
