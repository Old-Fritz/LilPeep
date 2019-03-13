<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение документа</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Users/Owner/JS/FormsLists.js" type="text/javascript"></script>
    </head>
    <body>
        <form method = "POST" action="/user/addedDocument">
            <table>
                <jsp:include page="../includes/leftPanelAdmin.jsp"/>
                <td class="content">
                    <h1>Изменение документа</h1>
                    <table  class="docitem" editable>
                        <td>
                            Название:<br>
                            <input name="name" type="text"/>
                            <br>
                            <br>
                            Описание:<br>
                            <input name = "description" type="text"/>
                            <br>
                            <br>
                            URL картинки:<br>
                            <input id="thInput" name = "picture" type="text"/>
                            <button type="button" onclick="document.getElementById('thumbnail').src = document.getElementById('thInput').value">Обновить миниатюру</button>
                            <br>
                            <br>
                        </td>
                        <td>
                            <image id="thumbnail" class="docimg" alt="Здесь могла быть ваша реклама"></image>
                        </td>
                    </table>
                    <br>
                    <div class="docField"></div>
                    <c:forEach var="field" items="${fields}">
                        <div id="${field.id}" class="docField">
                            <script>loadField(${field.id})</script>
                        </div>
                    </c:forEach>
                    <button type="button" onclick="createField()">Добавить поле</button>
                    <div class="buttonbar">
                        <button onclick="deleteDocumentKind()">Удалить</button>
                        <button onclick="window.history.back()">Отменить</button>
                        <button type="submit">Сохранить</button>
                    </div>
                </td>
            </table>

        </form>
    </body>
</html>