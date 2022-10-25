<%-- 
    Document   : home
    Created on : Oct 25, 2022, 12:10:02 PM
    Author     : Admin
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
            Welcome Instructor: ${sessionScope.account.displayname}<br/>
            <a href="instructor/timetable?insCode=${account.username}">Timetable</a><br/>
            <a href="logout">Logout.</a>
        </c:if>
        <c:if test="${sessionScope.account eq null}">
            you are not logged in yet!
        </c:if>
    </body>
</html>
