package br.com.pitagoras.factory;


import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/pablogos?serverTimezone=UTC", "root", "Pablo@2021");
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
    
}
