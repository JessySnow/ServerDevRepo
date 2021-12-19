<%@ page import="model.shell.ShellRunner" %>
<%@ page import="model.shell.ShellRet" %>

<html>
    <meta charset=utf-8" />
    <body>
        <h1>Hello World!</h1>
        <%
            ShellRunner runner = new ShellRunner();
            ShellRet ret = runner.run("ps");
            out.print("<h1>");
            out.println(ret.getRet());
            out.print("</h1>");
        %>
    </body>
</html>