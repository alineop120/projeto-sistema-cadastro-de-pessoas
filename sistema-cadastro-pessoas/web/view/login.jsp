<%-- 
    Document   : login
    Created on : 09/05/2025, 21:54:50
    Author     : Aline
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
        <form action="LoginController" method="post">
            <label>Email</label>
            <input type="email" name="email"/>
            <br/>
            <label>senha</label>
            <input type="password" name="senha"/>
            <br/>
            <input type="submit" value="entrar"/>
            <c:iftest="${param.erro}">
                <p style="color: red;">Credenciais inválidas!</p>
            </c:if>
        </form>
    </body>
</html>
