<%-- 
    Document   : listaUsuarios
    Created on : 09/05/2025, 11:56:34
    Author     : Aline
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Usuários</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-5">
    <!-- DEBUG: mostra se a lista chegou -->
    <c:out value="${usuarios}" default="Lista não recebida." />

    <h2 class="mb-4">Usuários Cadastrados</h2>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Nível de Acesso</th>
                 <th colspan="2">Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.nome}</td>
                    <td>${u.email}</td>
                    <td>
                        <c:if test="${u.nivelAcesso==1}">Admin</c:if>
                        <c:if test="${u.nivelAcesso!=1}">Usuario</c:if>
                    </td>
                    <td><button class="btn btn-warning" 
                        onclick="alterarUsuario(${usuario.id})">Editar</button></td>
                    <td><button class="btn btn-danger" 
                        onclick="deletarUsuario(${usuario.id})"><i
                        class="fa-solid fa-trash"></i> Excluir</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="index.jsp" class="btn btn-primary">Voltar para Cadastro</a>
    
    <script type="text/javascript">
        function deletarUsuario(id) {
            let confirma = window.confirm("Deseja excluir o Usuario de idº: " + id + "?");
            if (confirma) {
                window.location.href = "UsuarioController?action=deletar&id=" + id;
            }
        }
        function alterarUsuario(id){
            window.location.href = "UsuarioController?action=alterar&id="+id;
        }
    </script>
</body>
</html>
