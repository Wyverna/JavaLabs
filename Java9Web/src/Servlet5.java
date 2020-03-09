import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notfound")
public class Servlet5 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");

        try {
            String answer="<h2>Not Found: " + id + "</h2>";
            String path = request.getContextPath() + "/Servlet4?for="+answer;
            response.sendRedirect(path);
        } finally {
            writer.close();
        }
    }
}
