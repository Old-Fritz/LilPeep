<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение документа</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
    </head>
    <body>
        <form method = "POST" action="addedDocument">
            <input type="hidden" name="documentID" value="${document.id}">
            <table>
                <jsp:include page="../includes/leftPanelUser.jsp"/>
                <td class="content">
                    <h1>Добавление документа</h1>
                    <image width="128" height="128" src="${document.picture.url}" alt="Случился Бибиб"/>
                    <c:forEach var="field" items="${document.fields}" varStatus="status">
                        <h1>${field.name}</h1>
                        <input class="docfield" type="text" name="field${status.getIndex()}"/>
                    </c:forEach>
                </td>
            </table>
            <div class="buttonbar">
                <button onclick="window.history.back()">Отменить</button>
                <button type="submit">Сохранить</button>
            </div>
        </form>
    </body>
</html>