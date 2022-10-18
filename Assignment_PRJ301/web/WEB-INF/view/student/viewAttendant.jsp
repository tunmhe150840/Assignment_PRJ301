<%-- 
    Document   : viewAttendant
    Created on : Oct 18, 2022, 12:03:23 PM
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
        Student: <input type="text"  value="${requestScope.student.fullName}"/>
        <form action="viewAttendant" method="GET">
            <input type="hidden" name="stdCode" value="${param.stdCode}"/>
            Course:
            <select name="cCode">
                <c:forEach items="${requestScope.courses}" var="c">
                    <option value="${c.courseCode}"
                            <c:if test="${c.courseCode eq requestScope.cCode}">
                                selected="selected"
                            </c:if>accesskey="">${c.courseName}</option>
                </c:forEach>
            </select>
            <input type="submit" name="submit"> </br>
            <c:if test="${requestScope.cCode ne null}">
                Attendant:
                <table border = "1px">
                    <tr>
                        <td>No</td>
                        <td>Date</td>
                        <td>Slot</td>
                        <td>Room</td>
                        <td>Instructor</td>
                        <td>Group</td>
                        <td>Status</td>
                    </tr>
                    <c:forEach items="${requestScope.listAtt}" var="att">
                        <tr>
                            <td>${att.session.index}</td>
                            <td>${att.session.date}</td>
                            <td>${att.session.timeslot.description}</td>
                            <td>${att.session.room.roomName}</td>
                            <td>${att.session.group.supervisor.instructorCode}</td>
                            <td>${att.session.group.groupName}</td>
                            <td>
                                <c:if test="${att.isStatus()}">
                                    Attended
                                </c:if>
                                <c:if test="${!att.isStatus() and att.isStatus() ne null}">
                                    Absent
                                </c:if>
                                <c:if test="${att.isStatus() eq null}">
                                    Not yet
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </form>
    </body>
</html>
