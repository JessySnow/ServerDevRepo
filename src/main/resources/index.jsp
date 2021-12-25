<%@ page import="model.shell.ShellRunner" %>
<%@ page import="model.shell.ShellRet" %>
<%@ page import="model.stringprocessor.TopProcessor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/index.css"/>
    <body>
        <h2>|非闲置进程列表|CPU占用前三|内存占用前三|</h2>
        
        <div id=allprocess style="float: left">
        <%
            response.setIntHeader("Refresh", 5);
            ShellRet ret = new ShellRunner().run("top -b -n 1", new TopProcessor("<br>"));
            out.println(ret.getRet());
        %>
        </div>

        <%
            ShellRet[] rets = new ShellRunner().runs("top -b -n 1");
        %>

        <div id=cpuprocess style="float: left;">
            <%
                out.println(rets[0].getRet());
            %>
        </div>

        <div id=memprocess style="float: left;">
            <%
                out.println(rets[1].getRet());
            %>
        </div>
        
    </body>
</html>