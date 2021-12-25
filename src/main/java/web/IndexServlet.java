package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class IndexServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        writer.println("Hello Servlet");
    }
}
