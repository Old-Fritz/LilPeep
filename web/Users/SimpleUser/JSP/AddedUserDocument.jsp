<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Добавление документа</title>
    <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
</head>
<body>
<form method = "POST" action="addedDocument">
    <input type="hidden" name="documentID" value="${document.id}">
        <table>
            <jsp:include page="../includes/leftPanelUser.jsp"/>
            <td class="content">
                <h1>Добавление документа</h1>
                <table  class="docitem" onclick="window.location = '${action}?documentID=${document.id}'">
                    <tr>
                        <td>
                            <image class="docimg" src="${document.picture.url}" alt="Случился Бибиб"/>
                        </td>
                        <td>
                            <h1>${document.name}</h1>
                            <p>
                                ${document.description}
                            </p>
                        </td>
                    </tr>
                </table>
                <c:forEach var="field" items="${document.fields}" varStatus="status">
                    <br>
                    <br>
                    ${field.name}
                    <br>
                    <input class="docfield" type="text" name="field${status.getIndex()}"/>
                </c:forEach>
                <div class="buttonbar">
                    <button onclick="window.history.back()">Отменить</button>
                    <button type="submit">Сохранить</button>
                </div>
            </td>
        </table>
</form>
</body>
</html>