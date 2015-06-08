<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251"%>
<meta http-equiv="content-type" content="text/html; charset=cp1251">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- saved from url=(0040)http://getbootstrap.com/examples/signin/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Log in</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstram.min.css" rel="stylesheet" type="text/css">
    <link href="../resources/css/signin.css" rel="stylesheet" type="text/css">



<body>

    <div class="container">

        <form class="form-signin" method="POST" action="/controller">
            <input type="hidden" name="command" value="authentication" />

            <h2 class="form-signin-heading">Please log in</h2>
            <label for="inputEmail" class="sr-only">Login</label>
            <input type="text" userId="inputEmail" name="login" class="form-control" placeholder="Login" required="" autofocus="">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" userId="inputPassword" name="password" class="form-control" placeholder="Password" required="">
            <h6>Wrong login or password</h6>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>

    </div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="resources/js/ie10-viewport-bug-workaround.js"></script>


<script src="resources/js/pops"></script></body></html>

</body>
</html>