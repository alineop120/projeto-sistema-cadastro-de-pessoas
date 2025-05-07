<%-- 
    Document   : listaUsuarios
    Created on : 06/05/2025, 15:00:47
    Author     : 364975
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-5">
    <h1 class="mb-4">Usuários Cadastrados</h1>

    <table class="table table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Perfil</th>
                <th colspan="2">Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>
                        <c:if test="${usuario.nivelAcesso == 1}">Admin</c:if>
                        <c:if test="${usuario.nivelAcesso != 1}">Usuário</c:if>
                    </td>
                    <td><a href="#" class="btn btn-sm btn-warning">Alterar</a></td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deletarUsuario(${usuario.id})">Excluir</button>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty usuarios}">
                <tr>
                    <td colspan="6" class="text-center">Nenhum usuário cadastrado.</td>
                </tr>
            </c:if>
                
            <c:if test="${not empty msgSucesso}">
                <div class="alert alert-success">
                    ${msgSucesso}
                </div>
            </c:if>

            <c:if test="${not empty msgErro}">
                <div class="alert alert-danger">
                    ${msgErro}
                </div>
            </c:if>
        </tbody>
    </table>

    <div class="mt-3">
        <a href="${ctx}/index.jsp" class="btn btn-secondary">Voltar</a>
    </div>

    <script type="text/javascript">
        function deletarUsuario(id) {
            if (confirm("Deseja realmente excluir este usuário?")) {
                window.location.href = "${ctx}/controller/UsuarioController?action=deletar&id=" + id;
            }
        }
    </script>
</body>
</html>
