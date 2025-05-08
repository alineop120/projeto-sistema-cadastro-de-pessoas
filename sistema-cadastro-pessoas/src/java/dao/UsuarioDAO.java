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
            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM usuarios");
            ResultSet rs = ps.executeQuery()) {
             
            // Iterando sobre o ResultSet
            while (rs.next()) {
                // Criando o objeto Usuario e preenchendo com os dados do banco
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNivelAcesso(rs.getInt("acesso"));
                
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

    public void inserir(Usuario usuario) {
        try(Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement ps = conn.prepareStatement("insert into "
                    + "usuarios(nome,senha,email,acesso) values (?,?,?,?)");
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
            PreparedStatement ps = conn.prepareStatement("update usuarios "
                    + "set nome=?,senha=?,email=?,acesso=? where id=?");
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
    
    public Usuario buscarPorId(int id) {
    	Usuario usuario = null;
    	String sql = "SELECT * FROM usuarios WHERE id = ?";

    	try (Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	usuario = new Usuario();
            	usuario.setId(rs.getInt("id"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setEmail(rs.getString("email"));
            	usuario.setSenha(rs.getString("senha"));
            	usuario.setNivelAcesso(rs.getInt("acesso"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }
    
    public boolean deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;  // Retorna true se a exclusão foi bem-sucedida
        } catch (SQLException e) {
            return false;  // Retorna false em caso de erro
        }
    }
}
