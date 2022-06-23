package br.com.pitagoras.DAO;

import br.com.pitagoras.factory.ConnectionFactory;
import br.com.pitagoras.model.Candidato;
//import com.mysql.cj.jdbc.PreparedStatementWrapper;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CandidatoDAO {
    
    private Connection connection;
    
    private String nome;
    private String partido;
    private String cargo;
    private int numero;
    
    public CandidatoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Candidato candidato){
        String sql = "INSERT INTO candidato (Nome,Partido,Numero,Cargo) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getPartido());
            stmt.setInt(3, candidato.getNumero());
            stmt.setString(4, candidato.getCargo());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
