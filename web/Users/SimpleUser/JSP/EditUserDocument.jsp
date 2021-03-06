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
        <script src="../Resources/JS/js.js" type="text/javascript"></script>
    </head>
    <body>
    <div id="rabbitWindow"></div>
            <table>
                <jsp:include page="../includes/leftPanelUser.jsp"/>
                <td class="content">
                    <h1>Изменение документа</h1>
                    <image class="docimg" src="${document.documentKind.picture.url}" alt="Случился Бибиб"/>
                    <c:forEach var="field" items="${document.userDocumentFields}" varStatus="status">
                        <h1>${field.field.name}</h1>
                        <input type="text" name="field${status.getIndex()}Orig" onload="this.value = decryptStr(${field.value})" oninput="$('#field${status.getIndex()}').val(cryptStr(this.value))"/>
                    </c:forEach>
                    <form method="post" id="form" action="editDocument">
                        <input type="hidden" name="documentID" value="${document.id}">
                        <c:forEach var="field" items="${document.userDocumentFields}" varStatus="status">
                            <input id="field${status.getIndex()}" type="hidden" name="field${status.getIndex()}" value="${field.value}"/>
                        </c:forEach>
                    </form>
                    <div class="buttonbar">
                        <button onclick="$.get( 'editDocument',  {isDelete: true, documentID : '${document.id}'}, function(response) {window.location = '.'});">Удалить</button>
                        <button onclick="window.location = '.'">Отменить</button>
                        <button type="submit" form="form">Сохранить</button>
                    </div>

                </td>
            </table>

    </body>
</html>