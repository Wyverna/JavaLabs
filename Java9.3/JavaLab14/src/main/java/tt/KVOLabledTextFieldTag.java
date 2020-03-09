package tt;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class KVOLabledTextFieldTag extends SimpleTagSupport {
        private String label;
        private String value;
        private String name;
        public void setLabel(String lab) {
            this.label = lab;
        }
         public void setValue(String val) {
        this.value = val;
         }
         public void setName(String nam) {
        this.name = nam;
        }
        StringWriter sw = new StringWriter();
        public void doTag() throws JspException, IOException {
            JspWriter out = getJspContext().getOut();
            try {
                out.println("<input type='textbox' ");
                if (value != null) {
                    /* Use message from attribute */
                    out.println("value='" + value + "'");
                } else {
                    /* use message from the body */
                    getJspBody().invoke(sw);
                    getJspContext().getOut().println("value='" + sw.toString() + "'");
                }
                if (name != null) {
                    out.println("name='" + name + "'");
                }
                if (label == "true") {
                    out.println("<label ");
                    if (name != null) {
                        out.println("for='" + name + "'></label></input>");
                    } else {
                        out.println("></label></input>");
                    }
                } else {
                    out.println("</input>");
                }
            }
            catch(Exception e)
            {
                out.println(e.toString());
            }
        }
    }