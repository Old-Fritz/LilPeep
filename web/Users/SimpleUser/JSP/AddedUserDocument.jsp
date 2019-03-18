<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Добавление документа</title>
    <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="../Resources/JS/js.js" type="text/javascript"></script>
</head>
    <body>

    <div id="rabbitWindow"></div>
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
                                <p class="restrictedParagraph">
                                    ${document.description}
                                </p>
                            </td>
                        </tr>
                    </table>
                    <c:forEach var="field" items="${document.fields}" varStatus="status">
                        <h1>${field.name}</h1>
                        <input class="docfield" type="text" name="field${status.getIndex()}Orig" oninput="$('#field${status.getIndex()}').val(cryptStr(this.value))"/>
                    </c:forEach>
                    <form method = "POST" id="form" action="addedDocument">
                        <input type="hidden" name="documentID" value="${document.id}">
                        <c:forEach var="field" items="${document.fields}" varStatus="status">
                            <input class="docfield" id="field${status.getIndex()}" type="hidden" name="field${status.getIndex()}"/>
                        </c:forEach>
                    </form>
                    <div class="buttonbar">
                        <button onclick="window.location = '../user'">Отменить</button>
                        <button type="submit" form="form">Сохранить</button>
                    </div>
                </td>
            </table>

    </body>
</html>