<%-- 
    Document   : index
    Created on : 16/12/2018, 23:47:54
    Author     : Erika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center><h1 id="titulo">LOGIN</h1></center>

        <form action="Autentificar" method="POST" id="principal">
            <table border="0" id="tabla">
                <tbody>
                    <tr>
                        <center><td>USUARIO: </td></center>
                        <td><input type="text" name="txtusuario" value="" /></td>
                    </tr>
                    <tr>
                        <center><td>CONTRASEÑA: </td></center>
                        <td><input type="password" name="txtpass" value="" /></td>
                    </tr>
                    <tr>
                        <center><td><input id="boton" type="submit" value="INGRESAR" name="btingresar" /></td></center>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
