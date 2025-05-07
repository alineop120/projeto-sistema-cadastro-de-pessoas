<%-- 
    Document   : index
    Created on : 06/05/2025, 14:16:47
    Author     : 364975
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema de Cadastro de Pessoas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-5">
    <h1 class="mb-4">Cadastro de Usuários</h1>
    
    <!-- Formulário para cadastro e edição -->
    <form method="post" action="${ctx}/controller/UsuarioController" class="form-group">
        <input type="hidden" name="id" value="${usuario.id != null ? usuario.id : 0}"/>

        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" value="${usuario.nome != null ? usuario.nome : ''}" />
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${usuario.email != null ? usuario.email : ''}" />
        </div>

        <div class="mb-3">
            <label for="senha" class="form-label">Senha</label>
            <input type="password" class="form-control" id="senha" name="senha" value="${usuario.senha != null ? usuario.senha : ''}" />
        </div>

        <div class="mb-3">
            <label for="nivel" class="form-label">Nível de Acesso</label>
            <select name="nivel" class="form-select" id="nivel">
                <option value="1" ${usuario.nivelAcesso == 1 ? 'selected' : ''}>Admin</option>
                <option value="2" ${usuario.nivelAcesso == 2 ? 'selected' : ''}>Usuário</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Enviar</button>
    </form>

    <!-- Link para listar os usuários -->
    <div class="mt-3">
        <a href="${ctx}/controller/UsuarioController?action=listar" class="btn btn-secondary">Listar Usuários</a>
    </div>
</body>
</html>
