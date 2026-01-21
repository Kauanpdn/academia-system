package test;

import config.DatabaseConnection;
import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try {
            Connection conn = DatabaseConnection.getConnection();

            if (conn != null) {
                System.out.println("✅ Conexão realizada com sucesso!");
                conn.close();
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar no banco");
            e.printStackTrace();
        }
    }
}
