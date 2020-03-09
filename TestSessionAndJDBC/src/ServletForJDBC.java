import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import models.*;
import db.*;
import controllers.*;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

@WebServlet("/ServletForJDBC")
public class ServletForJDBC extends HttpServlet {
    final static Logger logger=Logger.getLogger(ServletForJDBC.class.toString());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String stringSum = request.getParameter("Sum");
            String stringSum2 = request.getParameter("Sum2");
            int sum=Integer.parseInt(stringSum)+Integer.parseInt(stringSum2);
            int sumAll=0;
            ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/Java8" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC", "root", "1111");
            UserRepository userController = new UserRepository(connectionPool);
            userController.getEntityBySum(sum);
            List<User> users = userController.getAll();
            PrintWriter writer=response.getWriter();
            writer.println("<table><tr><td>Year</td><td>Sum</td></tr>");
            for(User user:users) {
                sumAll+=user.getSum();
                writer.println("<tr><td>"+user.getYear()+"</td><td>"+user.getSum()+"</td></tr>");
            }
            writer.println("</table>");
            if(sumAll>0)
            {
                writer.println(sumAll);
            }
            else
            {
                request.getRequestDispatcher("/Welcome.jsp").forward(request,response);
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
