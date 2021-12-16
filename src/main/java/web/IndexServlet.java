package web;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import model.shell.ShellRunner;

public class IndexServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var out = response.getWriter();
    }
}
