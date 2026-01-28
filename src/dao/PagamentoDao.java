package dao;

import model.Pagamento;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class PagamentoDao {
    // CREAT
    public void cadastrar(Pagamento pagamento) {
        String sql = "INSERT INTO pagamento (matriculaId,valor,formaPagamento,status,dataVencimento,dataPagamento) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getMatriculaId());
            stmt.setDouble(2, pagamento.getValor());
            stmt.setString(3, pagamento.getFormaPagamento());
            stmt.setString(4, pagamento.getStatus());
            stmt.setDate(5, Date.valueOf(pagamento.getDataVencimento()));

            if (pagamento != null) {
                stmt.setDate(6, Date.valueOf(pagamento.getDataPagamento()));
            }

            stmt.executeQuery();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastra pagamento! ", e);
        }
    }

    // SELECT POR ID
    public Pagamento buscarPorId(int id) {
        String sql = "SELECT * FROM pagamento WHERE id = ?";
        Pagamento pagamento = null;

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setMatriculaId(rs.getInt("matriculaId"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setFormaPagamento(rs.getString("formaPagamento"));
                pagamento.setStatus(rs.getString("status"));
                pagamento.setDataVencimento(rs.getDate("dataVencimento").toLocalDate());

                Date dataPag = rs.getDate("dataPagamento");

                if (dataPag != null) {
                    pagamento.setDataPagamento(dataPag.toLocalDate());
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar matrícula por ID", e);
        }
        return pagamento;
    }

    // SELECT
    public List<Pagamento> Listar() {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM pagamento";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setMatriculaId(rs.getInt("matriculaId"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setFormaPagamento(rs.getString("formaPagamento"));
                pagamento.setStatus(rs.getString("status"));
                pagamento.setDataVencimento(rs.getDate("dataVencimento").toLocalDate());

                Date dataPag = rs.getDate("dataPagamento");

                if (dataPag != null) {
                    pagamento.setDataPagamento(dataPag.toLocalDate());
                }

                pagamentos.add(pagamento);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pagamentos! ", e);
        }

        return pagamentos;
    }

    // UPDATE TODOS
    public void atualizar(Pagamento pagamento) {
        String sql = "UPDATE pagamento SET " +
                "matricula_id = ?, valor = ?, forma_pagamento = ?, status = ?, " +
                "data_vencimento = ?, data_pagamento = ? " +
                "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pagamento.getMatriculaId());
            stmt.setDouble(2, pagamento.getValor());
            stmt.setString(3, pagamento.getFormaPagamento());
            stmt.setString(4, pagamento.getStatus());
            stmt.setDate(5, Date.valueOf(pagamento.getDataVencimento()));

            if (pagamento.getDataPagamento() != null) {
                stmt.setDate(6, Date.valueOf(pagamento.getDataPagamento()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setInt(7, pagamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pagamento ", e);
        }
    }

    // DELETE

    public void excluir(int id) {
        String sql = "DELETE FROM pagamento WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pagamento ", e);
        }
    }
}
