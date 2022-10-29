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
        <title>Timetable</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <style type="text/css">
            .style1 {
                font-weight: bold;
            }
            div {
                display: block;
            }
        </style>
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
                                    div{
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
                                </style>
                                <h2>Activities for
                                    <span>${requestScope.student.studentCode} (${requestScope.student.fullName})</span>
                                </h2>
                                <form action="timetable" method="GET">
                                    <input type="hidden" name="stdCode" value="${param.stdCode}"/>
                                    From: <input type="date" name="from" value="${requestScope.from}"/>
                                    To: <input type="date" name="to" value="${requestScope.to}"/>
                                    <input type="submit" value="View"/> 
                                </form>
                                <table style="margin: 5px">
                                    <thead background-color: blue>
                                        <tr>
                                            <th rowspan="2">

                                            </th>
                                            <c:forEach items="${requestScope.dates}" var="d">
                                                <th align="center">${d}</th>
                                                </c:forEach>

                                        </tr>
                                        <tr>
                                            <c:forEach items="${requestScope.dates}" var="d">
                                                <th align="center">${helper.getDayNameofWeek(d)}</th>
                                                </c:forEach>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.slots}" var="slot">
                                            <tr>
                                                <td style="width: 100px">Slot ${slot.slotID}</td>
                                                <c:forEach items="${requestScope.dates}" var="d">
                                                    <td style="color: #337ab7;
                                                        text-decoration: none;">
                                                        <c:forEach items="${requestScope.sessions}" var="ses">
                                                            <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.timeslot.slotID eq slot.slotID)}">
                                                                ${ses.group.groupName}_${ses.group.course.courseCode}
                                                                <br/>
                                                                at ${ses.room.roomName}
                                                                <c:if test="${ses.attendances.get(0).isStatus()}">
                                                                    <br>(<font color="Green">attended</font>)<br>
                                                                </c:if>
                                                                <c:if test="${!ses.attendances.get(0).isStatus() and ses.attendances.get(0).isStatus() ne null}">
                                                                    <br>(<font color="red">absent</font>)<br>
                                                                </c:if>
                                                                <c:if test="${ses.attendances.get(0).isStatus() eq null}">
                                                                    <br>(<font color="red">Not yet</font>)<br>
                                                                </c:if>
                                                                <span class="label label-success">(${slot.description})</span>
                                                            </c:if>

                                                        </c:forEach>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <p>
                                    <b>More note / Chú thích thêm</b>:
                                </p>
                                <div id="ctl00_mainContent_divfoot">
                                    <ul>
                                        <li>(<font color="green">attended</font>): <span style="text-transform: uppercase;">${requestScope.student.studentCode}</span> had attended
                                            this activity / ${requestScope.student.fullName} đã tham gia hoạt động này</li>
                                        <li>(<font color="red">absent</font>): <span style="text-transform: uppercase;">${requestScope.student.studentCode}</span> had NOT attended
                                            this activity / ${requestScope.student.fullName} đã vắng mặt buổi này</li>
                                        <li>(-): no data was given / chưa có dữ liệu</li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
