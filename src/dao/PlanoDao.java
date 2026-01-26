package dao;

import model.Plano;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class PlanoDao {

    // INSERT
    public void salvar(Plano plano) {

        String sql = "INSERT INTO plano (nome,valor,duracaoMeses,descricao) " + " VALUES(?,?,?,?) ";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setInt(3, plano.getDuracaoMeses());
            stmt.setString(4, plano.getDescricao());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar plano! ", e);
        }
    }

    // SELECT FOR ID
    public Plano buscarPorId(int id) {
        String sql = "SELECT * FROM plano WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Plano plano = new Plano();
                plano.setId(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                plano.setValor(rs.getDouble("valor"));
                plano.setDuracaoMeses(rs.getInt("duracaoMeses"));
                plano.setDescricao(rs.getString("descricao"));
                return plano;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar aluno por ID", e);
        }
    }

    // SELECT ALL
    public List<Plano> listarTodos() {
        String sql = "SELECT * FROM plano";
        List<Plano> planos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                Plano plano = new Plano();
                plano.setId(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                plano.setValor(rs.getDouble("valor"));
                plano.setDuracaoMeses(rs.getInt("duracaoMeses"));
                plano.setDescricao(rs.getString("descricao"));
            }

            return planos;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar alunos ", e);
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM plano WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar plano ", e);
        }
    }
}