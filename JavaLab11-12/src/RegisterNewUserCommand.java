import controllers.UserRepository;
import db.ConnectionPool;
import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

public class RegisterNewUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        String name = request.getParameter("Login");
        String password = request.getParameter("Password");
        String type = request.getParameter("Type");
        if (name.length()== 0 || password.length()== 0)
        {
            return "register.jsp";
        }
        try {
            ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/Java8" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC", "root", "1111");
            UserRepository userController = new UserRepository(connectionPool);
            userController.getEntityByIdandName(name,password);
            List<User> users=userController.getAll();
            if(users.size()>0) {
                request.setAttribute("errorMessage","Такой пользователь существует в базе данных");
            return "/register.jsp";
            } else {
                userController.Insert(name,password,type);
                return "/login.jsp";
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage",e.getMessage());
            System.out.println(e);
            return "failure";
        }
    }
}
