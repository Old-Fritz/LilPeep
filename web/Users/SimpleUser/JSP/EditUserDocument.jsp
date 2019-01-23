<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение документа</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
    </head>
    <body>
            <input type="hidden" name="documentID" value="${document.id}">
            <table>
                <jsp:include page="../includes/leftPanelUser.jsp"/>
                <td class="content">
                    <h1>Изменение документа</h1>
                    <image src="${document.documentKind.picture.url}" alt="Случился Бибиб"/>
                    <c:forEach var="field" items="${document.userDocumentFields}" varStatus="status">
                        <h1>${field.field.name}</h1>
                        <input type="text" name="field${status.getIndex()}" value="${field.value}"/>
                    </c:forEach>
                </td>
            </table>
            <div class="buttonbar">
                <button onclick="$.get( 'editDocument',  {isDelete: true, documentID : '${document.id}'}, function(response) {window.location = 'user/'});">Удалить</button>
                <button onclick="window.history.back()">Отменить</button>
                <button type="submit">Сохранить</button>
            </div>
        </form>
    </body>
</html>