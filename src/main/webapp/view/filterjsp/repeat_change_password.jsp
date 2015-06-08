<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251"%>
<meta http-equiv="content-type" content="text/html; charset=cp1251">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- saved from url=(0040)http://getbootstrap.com/examples/signin/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="resources/icon/favicon.ico">

    <title>Signin</title>

    <!-- Bootstrap core CSS -->
    <link href="../../resources/css/bootstram.min.css" rel="stylesheet" type="text/css">
    <link href="../../resources/css/signin.css" rel="stylesheet" type="text/css">



<body>

    <div class="container">

        <form class="change" id="change" method="POST" action="/change password page">
            <h4 class="form-signin-heading">Changing password for user "${user.name} ${user.surname}"</h4>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="password" id="inputEmail" name="oldPassword" class="form-control" placeholder="Old password" required="" autofocus="">
            <label for="newPassword" class="sr-only">Password</label>
            <input type="password" id="newPassword" name="password" class="form-control" placeholder="New password" required="">
            <input type="password" id="repeatPassword" name="password" class="form-control" placeholder="Repeat password" required="">
            <h6>Please, try again</h6>
            <button class="btn btn-lg btn-primary btn-block" type="submit" >Save</button>

            <c:choose>
                <c:when test="${user.role == 'owner'}">
                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="
                            document.getElementById('change').action='/view/filterjsp/owner_command.jsp'; document.getElementById('change').submit()">Cancel</button>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="
                            document.getElementById('change').action='/view/filterjsp/forester_command.jsp'; document.getElementById('change').submit()">Cancel</button>
                </c:otherwise>
            </c:choose>
        </form>
    </div> <!-- /container -->


</body></html>

</body>
</html>