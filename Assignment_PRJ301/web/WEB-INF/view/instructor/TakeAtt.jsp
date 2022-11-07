<%-- 
    Document   : Att
    Created on : Oct 24, 2022, 2:46:45 PM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <style>
                p span{
                    font-weight: bold;
                }

                th {
                    width: 150px;
                    border-right: 1px solid #fff;
                    text-align: center;
                    padding: 2px;
                    text-transform: uppercase;
                    height: 23px;
                    background-color: #6b90da;
                    font-weight: normal;
                }
            </style>
            <h1>Single activity Attendance</h1>
            <p>Attendance for <span>${requestScope.session.group.course.courseCode}</span> with lecturer <span>${requestScope.session.group.supervisor.instructorCode}</span> at slot ${requestScope.session.timeslot.slotID} on  ${requestScope.session.date}, in room ${requestScope.session.room.roomName}</p>
            Attended:
                <c:if test="${requestScope.session.getAttended()}">
                    <font color="Green">Yes</font>
                </c:if>
                <c:if test="${!requestScope.session.getAttended()}">
                    <font color="red">No</font>
                </c:if>   
            <form action="takeAtt" method="POST">
                <input type="hidden" name="sesid" value="${param.id}"/>
                <table style="margin: 5px">
                    <thead background-color: blue>
                        <tr>
                            <th>NO</th>
                            <th>GROUP</th>
                            <th>CODE</th>
                            <th>NAME</th>
                            <th>STATUS</th>
                            <th>TAKER</th>
                            <th>RECORD TIME</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.session.attendances}" var="att" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${requestScope.session.group.groupName}</td>
                                <td>${att.student.studentCode}
                                    <input type="hidden" name="stdCode" value="${att.student.studentCode}"/>
                                </td>
                                <td>${att.student.fullName}</td>
                                <td><input type="radio"
                                           <c:if test="${att.isStatus()}">
                                               checked="checked"
                                           </c:if>
                                           name="status${att.student.studentCode}" value="present" />
                                    <font color="Green">present</font>
                                    <input type="radio"
                                           <c:if test="${!att.isStatus()}">
                                               checked="checked"
                                           </c:if>
                                           name="status${att.student.studentCode}" value="absent" />
                                    <font color="red">absent</font>
                                </td>
                                <td>${requestScope.session.group.supervisor.instructorCode}</td>
                                <td>${att.recordTime}</td>
                            </tr>   
                        </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" name="insCode" value="${requestScope.session.group.supervisor.instructorCode}">
                <input type="submit" value="Save"/>
            </form>
        </div>
    </body>
</html>
