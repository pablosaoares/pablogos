package br.com.pitagoras.DAO;

import br.com.pitagoras.factory.ConnectionFactory;
import br.com.pitagoras.model.Eleitor;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class EleitorDAO {
    
    private Connection connection;
    
    private String nome;
    private String cpf;
    private int tituloEleitor;
    
    public EleitorDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Eleitor eleitor){
        String sql = "INSERT INTO eleitor (Nome,Titulo_Eleitor,CPF) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, eleitor.getNome());
            stmt.setInt(2, eleitor.getTituloEleitor());
            stmt.setString(3, eleitor.getCpf());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
