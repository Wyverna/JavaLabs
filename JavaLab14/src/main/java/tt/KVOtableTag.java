package tt;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
public class KVOtableTag extends SimpleTagSupport {
    StringWriter sw = new StringWriter();
    private int rows;
    public void setRows(int row) {
        this.rows = row;
    }
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        try {
            if(rows != 0)
            {
                out.println("<Table border='1'");
                getJspBody().invoke(sw);
                String body =sw.toString();
                String collection[]= body.split(",");
                int i = collection.length;
                int d=0;
                int numbercol=i/rows;
                if(i%rows!=0) {
                    numbercol++;
                }
                for (int j = 0; j < rows; j++) {
                    out.println("<tr>");
                    for(int q=0;q<numbercol;q++)
                    {
                        out.println("<td>");
                        if(collection[d]!=null)
                        {
                            out.println(collection[d]);
                            d++;
                        }
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
            }
        }
        catch (Exception e)
        {
            out.println(e.toString());
        }
    }
}
