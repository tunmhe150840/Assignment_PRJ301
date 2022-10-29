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
                <span class="label label-success">${sessionScope.account.username}</span></a> | <a href="logout" class="label label-success">logout</a> |
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
                                    .btn-warning {
                                        background-image: -webkit-linear-gradient(top,#f0ad4e 0,#eb9316 100%);
                                        background-image: -o-linear-gradient(top,#f0ad4e 0,#eb9316 100%);
                                        background-image: -webkit-gradient(linear,left top,left bottom,from(#f0ad4e),to(#eb9316));
                                        background-image: linear-gradient(to bottom,#f0ad4e 0,#eb9316 100%);
                                        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff0ad4e', endColorstr='#ffeb9316', GradientType=0);
                                        filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
                                        background-repeat: repeat-x;
                                        border-color: #e38d13;
                                    }
                                    .label-default {
                                        background-color: #777;
                                    }
                                    .label {
                                        display: inline;
                                        padding: 0.2em 0.6em 0.3em;
                                        font-size: 100%;
                                        font-weight: 700;
                                        line-height: 1;
                                        color: #fff;
                                        text-align: center;
                                        white-space: nowrap;
                                        vertical-align: baseline;
                                        border-radius: 0.25em;
                                    }
                                    .btn-warning {
                                        color: #fff;
                                        background-color: #f0ad4e;
                                        border-color: #eea236;
                                    }
                                    a {
                                        margin: 3px;
                                    }
                                </style>
                                <c:if test="${sessionScope.account ne null}">
                                    Welcome Instructor: <b>${sessionScope.account.displayname}</b><br/><br/>
                                    <div class="col-md-8">
                                        <span style="width: 200px;" class="label label-default btn-warning"><b>Function </b></span></br>
                                        <a href="instructor/timetable?insCode=${account.username}">Timetable</a><br/>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.account eq null}">
                                    <<h4 style="color: red">You are not logged in yet!</h4>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
