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
@WebServlet("/database")
public class Servlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("Login");
        String password=request.getParameter("Password");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try
        {
            ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/Java8"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC", "root", "1111");
            UserController userController=new UserController(connectionPool);
            userController.getEntityByIdandName(name,password);
            List<User> users=userController.getAll();
                if(users.size()>0) {
                        for (User user : users) {
                            Date date = new Date();
                            Cookie d=getCookie(request,"Count");
                            if(d!=null)
                            {
                              Integer x=Integer.parseInt(d.getValue())+1;
                              d.setValue(x.toString());
                              response.addCookie(d);
                            }
                            else {
                                Cookie f=new Cookie("Count","0");
                                response.addCookie(f);
                            }
                            Cookie as=new Cookie("Type",user.getType());
                            response.addCookie(as);
                            String pattern="yyyy-MM-dd";
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
                            String cookieData=simpleDateFormat.format(date);
                            Cookie s=new Cookie("Date",cookieData);
                            response.addCookie(s);
                            writer.println("<h5>Name:" + user.getName() + "<h5>");
                            writer.println("<h5>Password:" + user.getPassword() + "<h5>");
                            writer.println("<h5>Type:" + user.getType() + "<h5>");
                            writer.println("<h5>Date:" + date + "<h5>");
                        }
                    } else {
                    writer.println("<h5>Error Authorization</h5>");
                }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        finally {
            writer.close();
        }
    }
    private Cookie getCookie(HttpServletRequest request, String name)
    {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(name.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }
}
