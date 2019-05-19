package com.tlw.osgi.servlet.anno;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlet02", urlPatterns = "/servlet02", displayName = "servlet02_display_name", description = "servlet02_description")
public class AnnotationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Example</title>");
            writer.println("</head>");
            writer.println("<body align='center'>");
            writer.println("<h1>TLW's Example Annotation Servlet</h1>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
