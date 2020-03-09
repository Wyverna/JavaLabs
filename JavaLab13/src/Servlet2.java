import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import db.ConnectionPool;
import controllers.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.*;
import java.io.*;
import models.User;
import java.util.*;
@WebServlet(name="Servlet2",urlPatterns="/controller")
public class Servlet2 extends HttpServlet {
    private Map<String,Command> CommandMap;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String actionKey = request.getParameter("command");
        Command command = CommandMap.get(actionKey);
        String view = command.execute(request, response);
        if (view=="failure")
        {
            getServletContext().getRequestDispatcher("/errorpage.jsp").forward(request,response);
        }
        else {
            getServletContext().getRequestDispatcher(view).forward(request,response);
        }
        //Here, if view is if failure then, forward to jsp, to available request attributes in jsp.
        //      if view is success redirect to jsp..
    }
    @Override
    public void init() throws ServletException {
        CommandMap=new HashMap<String,Command>();
        CommandMap.put("login",new LoginCommand());
        CommandMap.put("loginpage",new LoginPageCommand());
        CommandMap.put("register",new RegisterNewUserCommand());
        CommandMap.put("RegisterPage",new RegisterPageCommand());
        CommandMap.put("SignOut",new SignOutCommand());
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request,response);
    }
}
