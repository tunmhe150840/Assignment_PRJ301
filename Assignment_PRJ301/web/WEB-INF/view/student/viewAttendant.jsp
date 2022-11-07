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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <style>

                ol {
                    display: block;
                    list-style-type: decimal;
                }
            </style>
            <div class="row">

                <div class="col-md-8">

                    <h1><span>FPT University Academic Portal</span>
                    </h1>

                </div>
                <div class="col-md-4">
                    <table>
                        <tbody>
                            <tr>
                                <td colspan="2" class="auto-style1"><strong>FAP mobile app (myFAP) is ready at</strong></td>

                            </tr>
                            <tr>
                                <td>
                                    <img src="../../public/img/app-store.svg" alt="app-store"/>
                                    <img src="../../public/img/play-store.svg" alt="play-store"/>
                                </td>
                            </tr>
                        </tbody></table>
                </div>
            </div>
            <div style="float: right; margin-right: 16px;">
                <span class="label label-success">${param.stdCode}</span></a> | <a href="../logout" class="label label-success">logout</a> |
                <span id="ctl00_lblCampusName" class="label label-success"> CAMPUS: FPTU-Hòa Lạc</span>
            </div>    
            <ol class="breadcrumb">
                <li>
                    <span id="ctl00_lblNavigation">
                        <b>View Schedule</b>&nbsp;|&nbsp;
                        <a href="javascript:history.back()">Back</a></span>
                </li>
            </ol>
            <table>
                <tbody>
                    <tr style="border-bottom: 0 none">
                        <td>
                            <div>
                                <style>
                                    p {
                                        margin: 2px;
                                    }
                                    div {
                                        margin: auto 0;
                                    }
                                    tbody th {
                                        text-align: left;
                                        padding: 2px;
                                    }
                                    a {
                                        color: #337ab7;
                                        text-decoration: none;
                                    }
                                    .label-success {
                                        background-color: #5cb85c;
                                    }
                                    th {
                                        border-right: 1px solid #fff;
                                        text-align: center;
                                        padding: 2px;
                                        text-transform: uppercase;
                                        height: 23px;
                                        background-color: #6b90da;
                                        font-weight: normal;
                                        width: 150px;
                                    }
                                    .label {
                                        display: inline;
                                        padding: 0.2em 0.6em 0.3em;
                                        font-size: 75%;
                                        font-weight: 700;
                                        line-height: 1;
                                        color: #fff;
                                        text-align: center;
                                        white-space: nowrap;
                                        vertical-align: baseline;
                                        border-radius: 0.25em;
                                    }
                                    .label-primary {
                                        background-color: #337ab7;
                                    }
                                    .label-danger {
                                        background-color: #d9534f;
                                    }
                                </style>
                                <h2>Activities for
                                    <span>${requestScope.student.studentCode} (${requestScope.student.fullName})</span>
                                </h2>
                                <div class="row"> 
                                    <div class="col-md-5">
                                        <h1>Select a campus/program, term, course ...</h1> 
                                        <h4 style="background: #6b90da">COURSE</h4>
                                        <c:forEach items="${requestScope.courses}" var="c">
                                            <p><a href="viewAttendant?stdCode=${requestScope.student.studentCode}&cCode=${c.courseCode}">${c.courseName}</a></p>
                                            </c:forEach>
                                    </div>
                                    <div class="col-md-7">
                                        <h1>... then see report</h1>

                                        <c:if test="${requestScope.cCode ne null}">
                                            <table style="margin: 5px">
                                                <thead background-color: blue>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Date</th>
                                                        <th>Slot</th>
                                                        <th>Room</th>
                                                        <th>Instructor</th>
                                                        <th>Group</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <c:if test="${requestScope.listAtt eq null}">
                                                    MDFKED
                                                </c:if>
                                                <c:forEach items="${requestScope.listAtt}" var="att">
                                                    <tr>
                                                        <td>${att.session.index}</td>
                                                        <td><span class="label label-primary">${att.session.date}</span></td>
                                                        <td><span class="label label-danger">${att.session.timeslot.slotID}_${att.session.timeslot.description}</span></td>
                                                        <td>${att.session.room.roomName}</td>
                                                        <td>${att.session.group.supervisor.instructorCode}</td>
                                                        <td>${att.session.group.groupName}</td>
                                                        <td>
                                                            <c:if test="${att.isStatus()}">
                                                                <br>(<font color="Green">Present</font>)<br>
                                                            </c:if>
                                                            <c:if test="${!att.isStatus() and att.isStatus() ne null}">
                                                                <br>(<font color="red">Absent</font>)<br>
                                                            </c:if>
                                                            <c:if test="${att.isStatus() eq null}">
                                                                <br>Future<br>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
