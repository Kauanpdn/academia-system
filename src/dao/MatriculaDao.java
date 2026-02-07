package dao;

import model.Matricula;
import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

//CADASTRAR MATRÍCULA
public class MatriculaDao {
    public void cadastrar(Matricula matricula) {
        String sql = "INSERT INTO matricula (alunoId, planoId, dataInicio, dataFim, status, valorContratado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(2, matricula.getPlanoId());
            stmt.setDate(3, java.sql.Date.valueOf(matricula.getDataInicio()));
            stmt.setDate(4, java.sql.Date.valueOf(matricula.getDataFim()));
            stmt.setString(5, matricula.getStatus());
            stmt.setDouble(6, matricula.getValorContratado());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar matrícula!", e);
        }
    }

    // BUSCANDO POR ID
    public Matricula buscarPorId(int id) {
        String sql = "SELECT * FROM matricula WHERE id = ? ";
        Matricula matricula = null;

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                matricula = new Matricula();
                matricula.setId(rs.getInt("id"));
                matricula.setAlunoId(rs.getInt("alunoId"));
                matricula.setPlanoId(rs.getInt("planoId"));
                matricula.setDataFim(rs.getDate("dataInicio").toLocalDate());
                matricula.setDataFim(rs.getDate("dataFim").toLocalDate());
                matricula.setStatus(rs.getString("status"));
                matricula.setValorContratado(rs.getDouble("valorContratado"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar matrícula por ID", e);
        }

        return matricula;
    }

    // LISTA POR ALUNO
    public List<Matricula> listarPorAluno(int alunoId) {
        List<Matricula> matriculas = new ArrayList<>();

        String sql = "SELECT * FROM matricula WHERE alunoId = ? ";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Matricula m = new Matricula();
                m.setId(rs.getInt("id"));
                m.setAlunoId(rs.getInt("alunoId"));
                m.setPlanoId(rs.getInt("planoId"));
                m.setDataFim(rs.getDate("dataInicio").toLocalDate());
                m.setDataFim(rs.getDate("dataFim").toLocalDate());
                m.setStatus(rs.getString("status"));
                m.setValorContratado(rs.getDouble("valorContratado"));

                matriculas.add(m);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar por alunos! ", e);
        }
        return matriculas;
    }

    // UPDATE COMPLETO
    public void atualizar(Matricula matricula) {
        String sql = "UPDATE matricula SET alunoId = ?, planoId = ?, dataInicio = ?, dataFim = ?, status = ?, valorContratado = ?  WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(2, matricula.getPlanoId());
            stmt.setDate(3, Date.valueOf(matricula.getDataInicio()));
            stmt.setDate(4, Date.valueOf(matricula.getDataFim()));
            stmt.setString(5, matricula.getStatus());
            stmt.setDouble(6, matricula.getValorContratado());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar matrícula ", e);
        }
    }

    public boolean existeMatriculaAtiva(int alunoId) {

        String sql = "SELECT COUNT(*) FROM matricula WHERE aluno_id = ? AND status = 'ATIVA'";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar matrícula ativa", e);
        }
    }

    // UPDATE ESPECÍFICO: STATUS
    public void atualizarStatus(int id, String status) {
        String sql = "UPDATE matricula set status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar matrícula ", e);
        }
    }

    // CANCELAR
    public void cancelar(int id) {
        atualizarStatus(id, "CANCELADA");
    }
}
