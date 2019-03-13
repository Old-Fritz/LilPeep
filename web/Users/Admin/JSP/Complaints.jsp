<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Поступившие жалобы</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
    </head>
    <body>
        <table>
            <jsp:include page="../includes/leftPanelAdmin.jsp"/>
            <td class="content">
                <h1>Поступившие жалобы</h1>
                <c:forEach var="complaint" items="${complaints}">
                    <div class="docitem">
                        <p>
                            Дата: ${complaint.date}
                            <br>
                            От: <b>${complaint.sender.email}</b>
                            <br>
                            На: <b>${complaint.user.email}</b>
                            <br>
                        </p>
                        <p>
                            Текст:
                            <br>
                            ${complaint.text}
                        </p>
                    </div>
                </c:forEach>
            </td>
        </table>
    </body>
</html>