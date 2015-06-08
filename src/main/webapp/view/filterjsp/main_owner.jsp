<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag.tld" prefix="ru" %>
<%--
  Created by IntelliJ IDEA.
  User: ru
  Date: 06.05.15
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Park</title>

    <link rel="icon" href="../../resources/icon/favicon.ico">
    <link href="../../resources/css/table.css" rel="stylesheet" type="text/css">
</head>
<body>
    <form id='form' method="POST" action="/controller">
        <input id="command" type="hidden" name="command"/>
        You sign in as owner
        <i><b id="currentUser">${user.name} ${user.surname}</b></i>
        <input type="submit" id="changePassword" value="Change password" onclick="
            document.getElementById('form').action='/view/filterjsp/change_password.jsp';
            document.getElementById('form').submit();">
        <input type="submit" id="logOut" value="Log out" onclick="
            document.getElementById('form').action='/controller';
            document.getElementById('command').value='logout';
            document.getElementById('form').submit()">

        <div>
            </br>
            <span>Park: <i><b id="park"> ${park.name} </b></i></span>  <span>Empty square: <i><b> ${park.emptySquare} </b></i> square metres </span>
            <span>Money: <i><b> ${user.money} </b></i>$</span>
        </div>

        </br>
        You can get a task for foresters of park
        <input type="button" id="taskButton" value="Get task" onclick="
            document.getElementById('plant').style.display='inline';
            document.getElementById('cancelTaskButton').style.display='inline';
            document.getElementById('okTaskMessage').style.display='none';">
        </br>
        </br>
        <select id="plant" style="display: none" name="plant" onchange="checkPlantSelect()">
            <option selected>Choose plant</option>
            <c:forEach var="plant" items="${plants}">
                <option value="${plant.plantId}">${plant.name}</option>
            </c:forEach>
        </select>

        <select id="work" style="display: none" name="work" onchange="checkWorkSelect();">
            <option selected>Choose work</option>
            <option value="seed">seed</option>
            <option value="dig">dig</option>
            <option value="water">water</option>
            <option value="sprinkle">sprinkle</option>
            <option value="mow">mow</option>
        </select>

        <input type="number" id="amount" name="amount" style="display: none" size="15" value="1" min="1">

        <select id="forester" style="display: none" name="forester" onchange="checkForesterSelect()">
            <option selected>Choose forester</option>
            <c:forEach var="forester" items="${foresters}">
                <option value="${forester.foresterId}">${forester.name} ${forester.surname}</option>
            </c:forEach>
        </select>
        </br>
        </br>
        <div>
            <input id="cancelTaskButton" value="Cancel" style="display: none" type="button" onclick="
                document.getElementById('plant').style.display='none';
                document.getElementById('work').style.display='none';
                document.getElementById('amount').style.display='none';
                document.getElementById('forester').style.display='none';
                document.getElementById('cancelTaskButton').style.display='none';
                document.getElementById('okTaskButton').style.display='none';
                document.getElementById('amountHint').style.display='none';">

            <input id="okTaskButton" value="Apply" style="display: none" type="submit" onclick="
                document.getElementById('command').value='task';
                this.submit();">
            <c:if test="${okTask != null}">
                <c:if test="${okTask == true}">
                    <ru:success value="true">
                        <i id="okTaskMessage"><b> Your task was sent successfully</b></i>
                    </ru:success>
                </c:if>
            </c:if>
        </div>

    </form>

<script>
    function checkWorkSelect(){
        var selectedValue = document.getElementById('work').value;
        if(selectedValue == 'Choose work') {
            document.getElementById('amount').style.display='none';
            document.getElementById('forester').style.display='none';
            document.getElementById('okTaskButton').style.display='none';
        } else {
            if(selectedValue == 'seed' || selectedValue == 'dig') {
                document.getElementById('amount').style.display='inline';
                document.getElementById('forester').style.display='inline';
            }  else {
                document.getElementById('amount').style.display='none';
                document.getElementById('forester').style.display='inline';
            }
        }
    }

    function checkPlantSelect() {
        var selectedValue = document.getElementById('plant').value;
        if(selectedValue == 'Choose plant') {
            document.getElementById('amount').style.display='none';
            document.getElementById('forester').style.display='none';
            document.getElementById('work').style.display='none';
            document.getElementById('okTaskButton').style.display='none';
        } else {
            document.getElementById('work').style.display='inline';
        }
    }

    function checkForesterSelect() {
        var selectedValue = document.getElementById('forester').value;
        if(selectedValue == 'Choose forester') {
            document.getElementById('okTaskButton').style.display='none';
        } else {
            document.getElementById('okTaskButton').style.display='inline';
        }
    }

</script>
</body>
</html>