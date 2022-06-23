package br.com.pitagoras.factory;


import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/pablogos?serverTimezone=UTC", "root", "Senha123");
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
    
}
