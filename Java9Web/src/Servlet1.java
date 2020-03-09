import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.net.*;
import java.io.*;
@WebServlet(urlPatterns = "/hello",name="Servlet1")
public class Servlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        InetAddress thisIp =InetAddress.getLocalHost();
        PrintWriter writer = response.getWriter();
        String url="null";
        String queryString="null";
        try {
            if (request instanceof HttpServletRequest) {
                url = ((HttpServletRequest)request).getRequestURL().toString();
                queryString = ((HttpServletRequest)request).getQueryString();
            }
            Date d1= new Date();
            writer.println("<h2>"+d1+"</h2>");
            writer.println("<h2>IP:"+request.getRemoteAddr()+"</h2>");
            writer.println("<h2>Name:"+request.getRemoteUser()+"</h2>");
            writer.println("<h2>Protocol:"+request.getProtocol()+"</h2>");
            writer.println("<h2>URL:"+url+"</h2>");
            writer.println("<h2>"+request.getMethod()+"</h2>");
            writer.println("<h2>"+request.getHeaderNames()+"</h2>");
        } finally {
            writer.close();
        }
    }
}