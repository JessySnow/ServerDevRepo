package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import model.shell.ShellRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class killServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String pid = request.getParameter("pid");
        new ShellRunner().run("kill -s 9 " + pid, "");

        // 跳转至 index.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
