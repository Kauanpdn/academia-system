package dao;

import model.Matricula;
import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

//CADASTRAR MATRÍCULA
public class MatriculaDao {
    public void cadastrar(Matricula matricula) {
        String sql = "INSERT INTO matricula (alunoId,planoId,dataInicio,dataFim,status,valorContratado)"
                + " VALUES (?,?,?,?,?,?)";

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
            throw new RuntimeException("Erro ao cadastra matrícula! ", e);
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
}
