<%-- 
    Document   : index
    Created on : 09/05/2025, 11:16:24
    Author     : Aline
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.com.etb.model.Usuario" %>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    boolean editando = usuario != null;
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Usuário</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">${editando ? "Editar Usuário" : "Cadastro de Usuário"}</h2>

    <!-- Botão de login -->
    <div class="mb-3 text-end">
        <a href="${pageContext.request.contextPath}/view/login.jsp" class="btn btn-outline-secondary">Ir para Login</a>
    </div>

    <!-- Formulário de Cadastro -->
    <form method="post" action="${pageContext.request.contextPath}/UsuarioController">
        <input type="hidden" name="id" value="${usuario != null ? usuario.id : 0}"/>

        <!-- Nome -->
        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" class="form-control" name="nome"
                   value="${usuario != null ? usuario.nome : ''}" required/>
        </div>

        <!-- Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" name="email"
                   value="${usuario != null ? usuario.email : ''}" required/>
        </div>

        <!-- Senha (somente em cadastro) -->
        <c:if test="${usuario == null}">
            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input type="password" class="form-control" name="senha" required/>
            </div>
        </c:if>

        <!-- Nível de Acesso -->
        <div class="mb-3">
            <label for="nivelAcesso" class="form-label">Nível de Acesso</label>
            <select class="form-select" name="nivelAcesso" required>
                <option value="1" ${usuario != null && usuario.nivelAcesso == 1 ? "selected" : ""}>Admin</option>
                <option value="2" ${usuario != null && usuario.nivelAcesso == 2 ? "selected" : ""}>Usuário</option>
            </select>
        </div>

        <!-- Botões -->
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">
                ${editando ? "Atualizar" : "Cadastrar"}
            </button>
            <a href="${pageContext.request.contextPath}/UsuarioController?acao=listar" 
               class="btn btn-secondary">Ver Lista de Usuários</a>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>