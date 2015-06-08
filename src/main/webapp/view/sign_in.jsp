<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251"%>
<meta http-equiv="content-type" content="text/html; charset=cp1251">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="en" value="en"/>
<c:set var="language"
       value="${not empty param.language ? param.language : en}"
       scope="request"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="current_language" var="lang"/>

<html lang="${language}">
    <link rel="icon" href="../resources/icon/favicon.ico">

    <title><fmt:message key="form.auth" bundle="${lang}"/></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstram.min.css" rel="stylesheet" type="text/css">
    <link href="../resources/css/signin.css" rel="stylesheet" type="text/css">



<body>

    <div class="container">
        <%--<form method="POST">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
            </select>
        </form>--%>

        <%--<form class="form-signin" method="POST" action="/authentication">--%>
        <form class="form-signin" method="POST" action="/controller">
            <h2 class="form-signin-heading"><fmt:message key="form.signin" bundle="${lang}"/></h2>

            <input type="hidden" name="command" value="authentication" />

            <label for="inputEmail" class="sr-only"><fmt:message key="form.login" bundle="${lang}"/></label>
            <input type="text" userId="inputEmail" name="login" class="form-control" placeholder="<fmt:message key="form.login" bundle="${lang}"/>" required="" autofocus="">
            <label for="inputPassword" class="sr-only"><fmt:message key="form.password" bundle="${lang}"/></label>
            <input type="password" userId="inputPassword" name="password" class="form-control" placeholder="<fmt:message key="form.password" bundle="${lang}"/>" required="">
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="form.in" bundle="${lang}"/></button>
        </form>

    </div> <!-- /container -->

</body>
</html>