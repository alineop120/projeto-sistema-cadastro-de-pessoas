package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import dao.UsuarioDAO;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author 364975
 */
@WebServlet(name = "/UsuarioController", urlPatterns = {"/controller/UsuarioController"})
public class UsuarioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if ("deletar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Tenta deletar o usuário
            boolean sucessoDeletar = usuarioDAO.deletar(id);

            if (sucessoDeletar) {
                request.setAttribute("msgSucesso", "Usuário deletado com sucesso!");
            } else {
                request.setAttribute("msgErro", "Erro ao tentar deletar o usuário.");
            }

            // Redireciona para a listagem após a exclusão
            response.sendRedirect(request.getContextPath() + "/controller/UsuarioController?action=listar");

        } else if ("alterar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Verifica se o usuário existe
            Usuario usuario = usuarioDAO.buscarPorId(id);

            if (usuario != null) {
                request.setAttribute("usuario", usuario);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } else {
                // Caso o usuário não exista
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuário não encontrado!");
            }

        } else {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            request.setAttribute("usuarios", usuarios);

            // Verifica se há usuários cadastrados
            if (usuarios.isEmpty()) {
                request.setAttribute("msgErro", "Nenhum usuário cadastrado.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listaUsuarios.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
    	String email = request.getParameter("email");
    	String senha = request.getParameter("senha");
    	int nivelAcesso = Integer.parseInt(request.getParameter("nivel"));

        Usuario usuario = new Usuario();
   	 
    	usuario.setNome(nome);
    	usuario.setSenha(senha);
    	usuario.setEmail(email);
    	usuario.setNivelAcesso(nivelAcesso);
        
        UsuarioDAO uDAO = new UsuarioDAO();
    	try {
            uDAO.inserir(usuario);
            System.out.println("Inserção realizada com sucesso");
            response.sendRedirect(request.getContextPath() + "/controller/UsuarioController?action=listar");
    	} catch (SQLException ex) {
            System.out.println(ex.getMessage());
    	}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
