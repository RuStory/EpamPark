<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <style type="text/css">
        td {
            /*font-family: Verdana, Arial, Helvetica, sans-serif;*/
            height: 20px;
            border: solid black 1px;
        }
    </style>
</head>
<body>
    <form id='form' method="POST" action="/controller">
        <input id = "command" type="hidden" name="command"/>
        You sign in as forester
        <i><b id="currentUser">${user.name} ${user.surname}</b></i>
        <input type="submit" id="changePassword" value="Change password" onclick="
            document.getElementById('form').action='/view/filterjsp/change_password.jsp';
            document.getElementById('form').submit()">
        <input type="submit" id="logOut" value="Log out" onclick="
            document.getElementById('form').action='/controller';
            document.getElementById('command').value='logout';
            document.getElementById('form').submit()">

        <div>
            </br>
            <span>Park: <i><b id="park"> ${park.name} </b></i></span>  <span>Empty square: <i><b> ${park.emptySquare} </b></i> square metres </span>
            <span>Salary: <i><b> ${user.salary} </b></i>$</span>
        </div>
        </br>

        Plants in your warehouse
        <div>
            <table style="border: 1px solid black">
                <tr>
                    <td>Plant</td>
                    <td>Count</td>
                    <td>Price</td>
                    <td>Square</td>
                </tr>
                <c:forEach var="warehousePlant" items="${warehouse.plantAmount}">
                    <tr>
                        <td>${warehousePlant.key.name}</td>
                        <td>${warehousePlant.value}</td>
                        <td>${warehousePlant.key.price}</td>
                        <td>${warehousePlant.key.square}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </br>
        <div>
            <c:choose>
                <c:when test="${tasks == 'any'}">
                    <p>No tasks for you today.</p>
                </c:when>
                <c:otherwise>
                    Task for you:
                    </br>
                    <c:forEach var="task" items="${tasks}">
                        <p>Please, ${task.description}
                            <c:if test="${task.count != 0}">
                                ${task.count}
                            </c:if>
                            <c:forEach var="plant" items="${plants}">
                                <c:if test="${plant.plantId == task.plantId}">
                                    <c:set var="plantName" scope="page" value="${plant.name}"/>
                                </c:if>
                            </c:forEach>
                        ${plantName}.</p>
                        <input type="submit" value="Execute" onclick="
                            document.getElementById('command').value='execute_task';
                            document.getElementById('executedTask').value=${task.taskId};
                            this.submit;">
                    </c:forEach>
                    <input id="executedTask" type="hidden" name="executedTask">
                </c:otherwise>
            </c:choose>

        </div>
    </form>
</body>
</html>