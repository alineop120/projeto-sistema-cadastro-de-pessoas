<%-- 
    Document   : listaUsuarios
    Created on : 06/05/2025, 15:00:47
    Author     : 364975
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <tr><th>ID</th><th>Nome</th><th>Email</th><th>Perfil</th><th colspan="2">Ações</th></tr>
            <c:forEach var="usuario" items="${usuarios}">
            	<tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>
                    	<c:if test="${usuario.nivelAcesso==1}">Admin</c:if>
                    	<c:if test="${usuario.nivelAcesso!=1}">Usuario</c:if>
                    </td>
                    <td>Alterar</td>
                    <td><button onclick=”deletarUsuario(${usuario.id})”>Excluir</button></td>
            	</tr>
            </c:forEach>
    	</table>
        <script type=”text/javascript”>
            function deletarUsuario(id){
                let confirma = window.confirm("Deseja deletar este usuário?");
                if(confirma){
                    window.location.href="controller/UsuarioController?action=deletar&id"+id;
                }
            }
        </script>
    </body>
</html>
