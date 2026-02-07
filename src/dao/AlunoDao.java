package dao;

import model.Aluno;
import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    // INSERT
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome,email,telefone,data_nascimento) VALUES (?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());

            if (aluno.getDataNascimento() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar aluno!", e);
        }
    }

    // SELECT POR ID
    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));

                java.sql.Date data = rs.getDate("data_nascimento");
                if (data != null) {
                    aluno.setDataNascimento(data.toLocalDate());
                }

                return aluno;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar aluno por ID", e);
        }
    }

    // SELECT ALL
    public List<Aluno> listarTodos() {

        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));

                java.sql.Date data = rs.getDate("data_nascimento");
                if (data != null) {
                    aluno.setDataNascimento(data.toLocalDate());
                }

                // BUG QUE CAUSAVA A LISTA VIR VAZIA
                alunos.add(aluno);
            }

            return alunos;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }
    }

    // UPDATE
    public void atualizar(Aluno aluno) {

        String sql = "UPDATE aluno SET nome = ?, email = ?, telefone = ?, data_nascimento = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());

            if (aluno.getDataNascimento() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.setInt(5, aluno.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    // DELETE
    public void deletar(int id) {

        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }
}
