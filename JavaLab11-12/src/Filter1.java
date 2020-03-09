import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Filter1 implements Filter
{
    private FilterConfig config = null;
    private boolean active = false;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void init (FilterConfig config) throws ServletException
    {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain) throws IOException,
            ServletException
    {
        if (active)
        {
            if(request.getParameter("command").equals("Welcome"))
            {
                if (request.getAttribute("Login")==null | request.getAttribute("Password")==null) {
                    request.setAttribute("errorMessage", "Нет авторизации для выполнения данной команды");
                    request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
                }
                else {
                    System.out.println(request.getParameter("Login"));
                    System.out.println(request.getParameter("Password"));
                    System.out.println(request.getParameter("command"));
                    chain.doFilter(request, response);
                    }
            }
                else {
                System.out.println(request.getParameter("Login"));
                System.out.println(request.getParameter("Password"));
                System.out.println(request.getParameter("command"));
                    chain.doFilter(request, response);
                }
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void destroy()
    {
        config = null;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
