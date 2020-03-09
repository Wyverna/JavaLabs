package tt;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class KVOsubmitTag extends SimpleTagSupport {
    StringWriter sw = new StringWriter();
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<br><input type='submit' value='Вход'/>");
        out.println(" <input type='button' value='Регистрация' onclick='location.href=\"controller?command=RegisterPage\"'/>");
    }
}
