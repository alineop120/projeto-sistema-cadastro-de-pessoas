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
    <h2 class="mb-4">Usuários Cadastrados</h2>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Nível de Acesso</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.nome}</td>
                    <td>${u.email}</td>
                    <td>${u.nivelAcesso}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="index.jsp" class="btn btn-primary">Voltar para Cadastro</a>
</body>
</html>
