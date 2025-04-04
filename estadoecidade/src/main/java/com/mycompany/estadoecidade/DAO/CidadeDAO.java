
package com.mycompany.estadoecidade.DAO;

import com.mycompany.estadoecidade.model.Estado;
import com.mycompany.estadoecidade.model.Cidade;
import com.mycompany.estadoecidade.Util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {

    
    public void add(Cidade cidade) {
        String sql = "INSERT INTO Cidade (nome, idestado) VALUES (?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cidade.getNome());
            stmt.setLong(2, cidade.getEstado().getId());

            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cidade.setId(generatedKeys.getLong(1)); 
                }
            }

            System.out.println("Cidade adicionada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cidade: " + e.getMessage());
        }
    }

   
    public List<Cidade> listar() {
        List<Cidade> cidades = new ArrayList<>();
        String sql = "SELECT c.id AS cidade_id, c.nome AS cidade_nome, " +
                 "e.id AS estado_id, e.nome AS estado_nome, e.uf AS estado_uf " +
                 "FROM Cidade c " +
                 "JOIN Estado e ON c.idestado = e.id";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getLong("id"));
                cidade.setNome(rs.getString("nome"));
                
                Estado estado = new Estado();
                estado.setId(rs.getLong("estado_id"));
                estado.setNome(rs.getString("estado_nome"));
                estado.setUf(rs.getString("estado_uf"));


                
                cidade.setEstado(estado);

                cidades.add(cidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar cidades: " + e.getMessage());
        }

        return cidades;
    }

    
    public void apagar(Long id) {
        String sql = "DELETE FROM Cidade WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cidade removida com sucesso!");
            } else {
                System.out.println("Cidade com ID " + id + " não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao apagar cidade: " + e.getMessage());
        }
    }
}

