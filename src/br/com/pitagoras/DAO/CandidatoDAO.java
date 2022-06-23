package br.com.pitagoras.DAO;

import br.com.pitagoras.factory.ConnectionFactory;
import br.com.pitagoras.model.Candidato;

//import com.mysql.cj.jdbc.PreparedStatementWrapper;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class CandidatoDAO {

    private Connection connection;
    ResultSet rs;
    ArrayList<Candidato> lista = new ArrayList<>();
    
    private String nome;
    private String partido;
    private String cargo;
    private int numero;
    private int id;
    
    

    public CandidatoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Candidato candidato) {
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
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<Candidato> PesquisarCandidato(){
        
        String sql = "select * from candidato;";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Candidato candidato = new Candidato();
                
                candidato.setId(rs.getInt("ID"));
                candidato.setNome(rs.getString("Nome"));
                candidato.setPartido(rs.getString("Partido"));
                candidato.setNumero(rs.getInt("Numero"));
                candidato.setCargo(rs.getString("Cargo"));
                
                lista.add(candidato);
            }
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar candidatos: "+erro);
        }
        return lista;
    }
}
