package br.com.pitagoras.DAO;

import br.com.pitagoras.factory.ConnectionFactory;

import br.com.pitagoras.model.Eleitor;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EleitorDAO {

    private Connection connection;
    ResultSet rs;
    ArrayList<Eleitor> lista = new ArrayList<>();

    private String nome;
    private String cpf;
    private int tituloEleitor;
    private int id;

    public EleitorDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Eleitor eleitor) {
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

    public ArrayList<Eleitor> PesquisarEleitor() {

        String sql = "select * from eleitor; ";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Eleitor eleitor = new Eleitor();

                eleitor.setId(rs.getInt("ID"));
                eleitor.setNome(rs.getString("Nome"));
                eleitor.setTituloEleitor(rs.getInt("Titulo_Eleitor"));
                eleitor.setCpf(rs.getString("CPF"));

                lista.add(eleitor);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar eleitor: " + erro);
        }
        return lista;
    }
}
