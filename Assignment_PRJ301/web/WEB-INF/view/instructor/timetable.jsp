<%-- 
    Document   : timetable
    Created on : Oct 19, 2022, 2:46:47 PM
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
        Lecturer: <input type="text" readonly="readonly" value="${requestScope.ins.instructorName}"/>
        <form action="timetable" method="GET">
            <input type="hidden" name="insCode" value="${param.insCode}"/>
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
                                    <a href="takeAtt?id=${ses.sessionID}">${ses.group.groupName}-${ses.group.course.courseCode}</a>
                                    <br/>
                                    ${ses.room.roomName}
                                    <c:if test="${ses.isAttended()}">
                                        Attended
                                    </c:if>
                                    <c:if test="${!ses.isAttended() and ses.isAttended() ne null}">
                                        Absent
                                    </c:if>
                                    <c:if test="${ses.isAttended() eq null}">
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
