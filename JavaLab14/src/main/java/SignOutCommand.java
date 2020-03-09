import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.removeAttribute("Login");
            request.removeAttribute("Password");
            return "/login.jsp";
        }
        catch (Exception e) {
            return "failure";
        }
    }
}