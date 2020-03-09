import javax.servlet.http.HttpServletRequest;

import db.ConnectionPool;
import controllers.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import models.User;
import java.util.*;
public class LoginCommand implements Command {
    public String Login;
    public String Password;
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        Login = request.getParameter("Login");
        Password = request.getParameter("Password");
        request.setAttribute("Login",Login);
        request.setAttribute("Password",Password);
        try {
            ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/Java8" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC", "root", "1111");
            UserRepository userController = new UserRepository(connectionPool);
            userController.getEntityByIdandName(Login, Password);
            List<User> users = userController.getAll();
            if (users.size() > 0) {
                userController.Users.clear();
                userController.getAllBD();
                users=userController.getAll();
                request.setAttribute("items",users);
                return "/Welcome.jsp";
            }
            else{
                request.setAttribute("errorMessage","Такого пользователя не существует в базе данных");
                return "/login.jsp";
            }
        }
        catch (SQLException e) {
            request.setAttribute("errorMessage",e.getMessage());
            System.out.println(e);
            return "failure";
        }
    }
}
