<%-- 
    Document   : timetable
    Created on : Oct 17, 2022, 7:34:17 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Student: <input type="text" readonly="readonly" value="${requestScope.student.fullName}"/>
        <form action="timetable" method="GET">
            <input type="hidden" name="stdCode" value="${param.stdCode}"/>
            From: <input type="date" name="from" value="${requestScope.from}"/>
            To: <input type="date" name="to" value="${requestScope.to}"/>
            <input type="submit" value="View"/> 
        </form>
        <table border="1px">
            <tr>
                <td> </td>
            <c:forEach items="${requestScope.dates}" var="d">
                <td>${d}<br/>${helper.getDayNameofWeek(d)}</td>
            </c:forEach>
        </tr>
        <c:forEach items="${requestScope.slots}" var="slot">
            <tr>
                <td>${slot.description}</td>
            <c:forEach items="${requestScope.dates}" var="d">
                <td>
                <c:forEach items="${requestScope.sessions}" var="ses">
                    <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.timeslot.slotID eq slot.slotID)}">
                        ${ses.group.groupName}_${ses.group.course.courseCode}
                        <br/>
                        ${ses.room.roomName}
                        <c:if test="${ses.attended}">
                            Attended
                        </c:if>
                        <c:if test="${!ses.attended}">
                            Not yet
                        </c:if>
                    </c:if>

                </c:forEach>
                </td>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
