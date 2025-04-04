
package com.mycompany.estadoecidade.DAO;

import com.mycompany.estadoecidade.model.Estado;
import com.mycompany.estadoecidade.Util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {

    
    public void add(Estado estado) {
        String sql = "INSERT INTO Estado (nome, uf) VALUES (?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getUf());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    estado.setId(generatedKeys.getLong(1)); 
                }
            }

            System.out.println("Estado adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar estado: " + e.getMessage());
        }
    }

    
    public List<Estado> listar() {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT id, nome, uf FROM Estado";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getLong("id"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));

                estados.add(estado);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar estados: " + e.getMessage());
        }

        return estados;
    }

    
    public Estado buscarPorId(Long id) {
        String sql = "SELECT id, nome, uf FROM Estado WHERE id = ?";
        Estado estado = null;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estado = new Estado();
                estado.setId(rs.getLong("id"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar estado por ID: " + e.getMessage());
        }

        return estado;
    }

    
    public void apagar(Long id) {
        String sql = "DELETE FROM Estado WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Estado removido com sucesso!");
            } else {
                System.out.println("Estado com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao apagar estado: " + e.getMessage());
        }
    }
}
