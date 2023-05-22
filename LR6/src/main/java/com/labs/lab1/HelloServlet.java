package com.labs.lab1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
//создаем строку, в которой будем искать слово
    private String text = "Apache Tomcat is an open-source web server and servlet container developed by the Apache Software Foundation (ASF). Tomcat implements several Java EE specifications including Java Servlet, JavaServer Pages (JSP), Java EL, and WebSocket, and provides a \"pure Java\" HTTP web server environment for Java code to run in.\n" +

            "\n" +

            "Tomcat is developed and maintained by an open community of developers under the auspices of the Apache Software Foundation, released under the Apache License 2.0 license, and is open-source software.";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");/* Устанавливает тип контента ответа, отправляемого клиенту, если ответ еще не фиксировался.*/
        request.setCharacterEncoding("UTF-8"); /* Устанавливает кодировку символов (набор символов MIME) ответа, отправляемого клиенту, например, UTF-8.*/
        PrintWriter out = response.getWriter();/*Возвращает в PrintWriter объект, который может отправить символьный текст клиенту.*/
        String find = request.getParameter("Find");/* возвращает значение параметров в форматеString */
        int l = text.length();//возвращает длину строки

        String text1 = text.replaceAll(find, "");// заменяет все вхождения строк, удовлетворяющих регулярному выражению, указанной строкой;

        int q = (l - text1.length()) / find.length();//получаем результат

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Text Redactor Server</title>");
            out.println("</head>");
            out.println("<h1 align='center'><font face = 'Calibri'><b>The result of searching:</b></font><br></h1>");
            out.println("<body>");
            out.println("<center>Number of repetitions of word <span style=\" font-weight:bold; \">" + find + "</span> in the text: " + q + "</center><br></br>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request. setAttribute("text", text);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("text", text);
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}