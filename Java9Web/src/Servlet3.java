import java.io.*;
import controllers.UserController;
import db.ConnectionPool;
import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;
@WebServlet(name="Servlet3",urlPatterns = "/databaseregistrate")
public class Servlet3 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Login");
        String password = request.getParameter("Password");
        String type = request.getParameter("Type");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/Java8" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC", "root", "1111");
            UserController userController = new UserController(connectionPool);
            userController.getEntityByIdandName(name,password);
            List<User> users=userController.getAll();
            if(users.size()>0) {
                for (User user : users) {
                    writer.println("<h5>Error Registration</h5>");
                }
            } else {
                userController.Insert(name,password,type);
                writer.println("<h5>Success Registration</h5>");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            writer.close();
        }
    }
}
