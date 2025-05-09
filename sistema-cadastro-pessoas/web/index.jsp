<%-- 
    Document   : index
    Created on : 09/05/2025, 11:16:24
    Author     : Aline
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Usuário</title>
    
    <!-- Link do Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Cadastro de Usuário</h2>

        <form method="post" action="UsuarioController">
            <input type="hidden" name="id" value="${usuario.id != null ? usuario.id : 0}"/>

            <!-- Nome -->
            <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" class="form-control" name="nome" value="${usuario.nome != null ? usuario.nome : ''}" required/>
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" name="email" value="${usuario.email != null ? usuario.email : ''}" required/>
            </div>

            <!-- Senha -->
            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input type="password" class="form-control" name="senha" value="${usuario.senha != null ? usuario.senha : ''}" required/>
            </div>

            <!-- Nível de Acesso -->
            <div class="mb-3">
                <label for="nivel" class="form-label">Nível de Acesso</label>
                <select class="form-select" name="nivel" required>
                    <option value="1" ${usuario.nivel == 1 ? 'selected' : ''}>Admin</option>
                    <option value="2" ${usuario.nivel == 2 ? 'selected' : ''}>Usuário</option>
                </select>
            </div>

            <!-- Botões Lado a Lado -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary w-48">Cadastrar</button>
                <a href="UsuarioController?action=listar" class="btn btn-secondary w-48">Ver Lista de Usuários</a>
            </div>
        </form>
    </div>

    <!-- Script do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
