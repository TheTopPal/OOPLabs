<%@page contentType="text/html; charset=UTF-8" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script>
        function fun1(){
            var form = document.getElementById("form1");
            form.submit();
        }
    </script>
    <title>Finder</title>
</head>

<style>
    h1
    {
        font-family: 'Calibri';
    }
</style>

<form action="HelloServlet" method="post" id="form1">
    <h1>Введите слово:</h1>
    <hr>
    <table>
        <tr>
            <textarea name="text" type="text" style="width: 60%; height: 200px;" disabled="true">Apache Tomcat is an open-source web server and servlet container developed by the Apache Software Foundation (ASF). Tomcat implements several Java EE specifications including Java Servlet, JavaServer Pages (JSP), Java EL, and WebSocket, and provides a "pure Java" HTTP web server environment for Java code to run in.

Tomcat is developed and maintained by an open community of developers under the auspices of the Apache Software Foundation, released under the Apache License 2.0 license, and is open-source software.</textarea>
        </tr>
        <tr>
            <td>
                <b>Найти слово: </b>
                <input id="find" type="text" name="Find">
            </td>
        </tr>
    </table>
    <hr>
    <input type="button" value="View Result" onclick = "fun1();">
</form>
</html>