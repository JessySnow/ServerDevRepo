<%@ page import="model.shell.ShellRunner" %>
<%@ page import="model.shell.ShellRet" %>
<%@ page import="model.stringprocessor.TopProcessor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- <head rel="stylesheet" type="text/css" href=".css/index.css"></head> -->
    <body>
        <h2>|非闲置进程列表|CPU占用前三|内存占用前三|</h2>
        
        <div id=allprocess>
        <%
            response.setIntHeader("Refresh", 5);
            ShellRet ret = new ShellRunner().run("top -b -n 1", new TopProcessor("<br>"));
            out.println(ret.getRet());
        %>
        </div>

        <div id=cpuprocess>
        </div>

        <div id=memprocess>
        </div>
        
    </body>
</html>