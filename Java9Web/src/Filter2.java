import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Filter2 implements Filter
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
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        Cookie d=getCookie(req,"Type");
        if(d==null) {
        res.sendRedirect(req.getContextPath()+"/authorize.jsp");
        }
        chain.doFilter(request, response);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void destroy()
    {
        config = null;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
