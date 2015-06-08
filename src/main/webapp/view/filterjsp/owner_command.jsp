<%--
  Created by IntelliJ IDEA.
  User: ru
  Date: 04.06.15
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form id="form" method="POST" action="/controller">

        <input type="hidden" name="command" value="owner" />
    </form>
    <script>document.getElementById('form').submit();</script>
</body>
</html>