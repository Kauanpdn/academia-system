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
    public void salvar(Plano plano){

        String sql = "INSERT INTO plano (nome,valor,duracaoMeses,descricao) " + " VALUES(?,?,?,?) ";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setInt(3, plano.getDuracaoMeses());
            stmt.setString(4, plano.getDescricao());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar plano! ", e);
        }
    }
    
}
