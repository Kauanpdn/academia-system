package dao;

import model.Pagamento;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;



import java.util.List;
import java.util.ArrayList;

public class PagamentoDao {
    // CREAT
    public void cadastrar(Pagamento pagamento) {
        String sql = "INSERT INTO pagamento (matriculaId,valor,formaPagamento,status,dataVencimento,dataPagamento) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
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
    // SELECT TODOS
    // UPDATE TODOS
    // DELETE
}
