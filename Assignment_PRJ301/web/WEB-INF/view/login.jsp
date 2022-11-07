<%-- 
    Document   : login
    Created on : Oct 17, 2022, 10:00:33 AM
    Author     : sonnt
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
                input{
                    margin: 3px;
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
            </div>
            <div class="col-md-8">
                <span class="label label-default btn-warning"><b>Sinh viên, Giảng viên, Cán bộ ĐH-FPT</b></span>
                <h3 style="color: red">${requestScope.error}</h3>
                <h3 style="color: green">${requestScope.logout}</h3>
                <form action="login" method="POST">
                    <p>
                        <label>Username:</label> <input type="text" name="username" autocomplete="off" value="${cookie.cookuser.value}"/>
                    </p>
                    <p>
                        <label>Password:</label> <input type="password" name="password"  autocomplete="off" value="${cookie.cookpass.value}"/>
                    </p>
                    <p>
                        <label>Remember:</label> <input type="checkbox" name="remember" value="1"
                                                        <c:if test="${cookie.cookrem.value  ne null}">
                                                            checked="checked"
                                                        </c:if>
                                                        >
                    </p>
                    <input type="submit" value="Login" /> 
                </form>
            </div>
        </div>
    </body>
</html>
