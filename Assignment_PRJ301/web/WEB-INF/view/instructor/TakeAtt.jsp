<%-- 
    Document   : Att
    Created on : Oct 24, 2022, 2:46:45 PM
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
        Take attendance for Group: ${requestScope.session.group.groupName} <br/>
        Subject: ${requestScope.session.group.course.courseName} <br/>
        Room: ${requestScope.session.room.roomName} <br/>
        Date: ${requestScope.session.date}<br/>
        Instructor: ${requestScope.session.group.supervisor.instructorCode} <br/>
        Attended:<p> 
            <c:if test="${requestScope.session.getAttended()}">
                Yes
            </c:if>
            <c:if test="${!requestScope.session.getAttended()}">
                No
            </c:if>   
        </p>
        <form action="takeAtt" method="POST">
            <input type="hidden" name="sesid" value="${param.id}"/>
            <table border="1px">
                <tr>
                    <td>No.</td>
                    <td>StudentID</td>
                    <td>Full Name</td>
                    <td>Present</td>
                    <td>Absent</td>
                </tr>
                <c:forEach items="${requestScope.session.attendances}" var="att" varStatus="loop">
                    <tr>
                        <td>${loop.index+1}</td>
                        <td>${att.student.studentCode}
                            <input type="hidden" name="stdCode" value="${att.student.studentCode}"/>
                        </td>
                        <td>${att.student.fullName}</td>
                        <td><input type="radio"
                                   <c:if test="${att.isStatus()}">
                                       checked="checked"
                                   </c:if>
                                   name="status${att.student.studentCode}" value="present" /></td>
                        <td><input type="radio"
                                   <c:if test="${!att.isStatus()}">
                                       checked="checked"
                                   </c:if>
                                   name="status${att.student.studentCode}" value="absent" /></td>
                    </tr>   

                </c:forEach>

            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
