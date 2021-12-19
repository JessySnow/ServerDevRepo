<%@ page import="model.shell.ShellRunner" %>
<%@ page import="model.shell.ShellRet" %>
<%@ page import="model.stringprocessor.StyleProcess" %>

<html>
    <meta charset=utf-8" />
    <body>
        <h1>Hello World!</h1>
        <%
            ShellRunner runner = new ShellRunner();
            ShellRet ret = runner.run("ps", new StyleProcess("", "<br>"));
            out.println(ret.getRet());
        %>
    </body>
</html>