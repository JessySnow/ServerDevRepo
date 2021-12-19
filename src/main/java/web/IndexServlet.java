package web;

import java.io.IOException;
import javax.servlet.http.*;
import model.shell.ShellRunner;

public class IndexServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var out = response.getWriter();
        var ret = new ShellRunner().run("whoami");
        out.println("Welcome " + ret.getRet() + " my Boss!");
    }
}
