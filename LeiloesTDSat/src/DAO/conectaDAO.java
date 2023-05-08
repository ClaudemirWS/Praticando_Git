package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adm
 */
public class conectaDAO {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public conectaDAO() {
        this.conn = conectar();
    }

    //Faz a conexão com o banco de dados
    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "admin");
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    //Desconecta do banco de dados
    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }

}
