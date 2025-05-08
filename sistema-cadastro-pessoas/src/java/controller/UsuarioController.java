package controller;

import model.Usuario;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int id = Integer.parseInt(request.getParameter("id"));
                    
        if (null == action) {
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            request.setAttribute("usuarios", usuarios);
            // Verifica se há usuários cadastrados
            if (usuarios.isEmpty()) 
            {
                request.setAttribute("msgErro", "Nenhum usuário cadastrado.");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listaUsuarios.jsp");
            dispatcher.forward(request, response);
        } 
        else switch (action) {
            case "deletar":
                // Tenta deletar o usuário
                boolean sucessoDeletar = usuarioDAO.deletar(id);
                if (sucessoDeletar) {
                    request.setAttribute("msgSucesso", "Usuário deletado com sucesso!");
                }
                else {
                    request.setAttribute("msgErro", "Erro ao tentar deletar o usuário.");
                }   // Redireciona para a listagem após a exclusão
                response.sendRedirect(request.getContextPath() + "/controller/UsuarioController?action=listar");
                break;
            case "alterar":
                // Verifica se o usuário existe
                Usuario usuario = usuarioDAO.buscarPorId(id);
                request.setAttribute("usuario", usuario);
                RequestDispatcher rs = request.getRequestDispatcher("/index.jsp");
                rs.forward(request, response);
                break;
            default:
                List<Usuario> usuarios = usuarioDAO.listarTodos();
                request.setAttribute("usuarios", usuarios);
                // Verifica se há usuários cadastrados
                if (usuarios.isEmpty())
                {
                    request.setAttribute("msgErro", "Nenhum usuário cadastrado.");
                }   RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listaUsuarios.jsp");
                dispatcher.forward(request, response);
                break;
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
    	int nivel = Integer.parseInt(request.getParameter("acesso"));
        
        Usuario u = new Usuario();
   	 
    	u.setNome(nome);
    	u.setSenha(senha);
    	u.setEmail(email);
    	u.setNivelAcesso(nivel);
        
        UsuarioDAO uDAO = new UsuarioDAO();
    	if (Integer.parseInt(request.getParameter("id")) == 0){
            uDAO.inserir(u);
    	} else {
            int id = Integer.parseInt(request.getParameter("id"));
            u.setId(id);
            uDAO.atualizar(u);
    	}
        response.sendRedirect("./UsuarioController");
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
