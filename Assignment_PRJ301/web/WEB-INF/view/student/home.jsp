<%-- 
    Document   : home
    Created on : Oct 17, 2022, 10:28:01 AM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.account ne null}">
            Hello Student: ${sessionScope.account.displayname} <br/>
            <a href="student/timetable?stdCode=${sessionScope.account.username}">Timetable</a> <br/>
            <a href="student/viewAttendant?stdCode=${sessionScope.account.username}">View Attendant</a> <br/>
            <a href="logout">Logout.</a> 
        </c:if>
        <c:if test="${sessionScope.account eq null}">
            you are not logged in yet!
        </c:if>
    </body>
</html>
