import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet4")
public class Servlet4 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null) {

            String path = request.getContextPath() + "/notfound?id="+id;
            response.sendRedirect(path);
        }
        else {
            String answer = request.getParameter("for");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            try {
                writer.println("<h2>"+answer+"</h2>");
            } finally {
                writer.close();
            }
        }
    }
}
