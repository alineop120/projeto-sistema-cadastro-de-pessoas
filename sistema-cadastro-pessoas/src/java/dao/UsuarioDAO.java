package dao;

import java.sql.*;
import model.Usuario;
import java.util.*;
import util.ConnectionFactory;

/**
 *
 * @author 364975
 */
public class UsuarioDAO {

    public List<Usuario> listarTodos() {
        // Usando o tipo genérico correto para a lista
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        // Conectando ao banco de dados
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios");
             ResultSet rs = ps.executeQuery()) {
             
            // Iterando sobre o ResultSet
            while (rs.next()) {
                // Criando o objeto Usuario e preenchendo com os dados do banco
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNivelAcesso(rs.getInt("nivel"));
                
                // Adicionando à lista
                listaUsuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            // Logando o erro para facilitar a depuração
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        
        // Retorna a lista de usuários
        return listaUsuarios;
    }

    public void inserir(Usuario usuario) throws SQLException {
        try(Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement ps = conn.prepareStatement("insert into usuarios(nome,senha,email,nivel) values (?,?,?,?)");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, usuario.getNivelAcesso());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.getMessage();
        }
    }

    public void atualizar(Usuario usuario) throws SQLException {
    	try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("update usuarios set nome=?,senha=?,email=?,nivel=? where id=?");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, usuario.getNivelAcesso());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
    	}
    }

    
    public void deletar(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("delete from usuarios where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
