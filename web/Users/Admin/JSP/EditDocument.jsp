<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение документа</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css?v=52">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Users/Admin/JS/admin.js?v=10" type="text/javascript"></script>
    </head>
    <body>
        <table>
            <jsp:include page="../includes/leftPanelAdmin.jsp"/>
            <td class="content">
                <h1>Изменение документа</h1>


                <table  class="docitem" editable>
                    <td>
                        Название:<br>
                        <input name="name" type="text" value="${documentKind.name}" oninput="$('#name').val(this.value)"/>
                        <br>
                        <br>
                        Описание:<br>
                        <input name = "description" type="text" value="${documentKind.description}" oninput="$('#description').val(this.value)"/>
                        <br>
                        <br>
                        URL картинки:<br>
                        <input id="thInput" name = "picture" type="text" value="${documentKind.picture.url}" oninput="$('#picture').val(this.value)"/>
                        <button type="button" onclick="document.getElementById('thumbnail').src = document.getElementById('thInput').value">Обновить миниатюру</button>
                        <br>
                        <br>
                    </td>
                    <td>
                        <image id="thumbnail" class="docimg" alt="Здесь могла быть ваша реклама" src="${documentKind.picture.url}"></image>
                    </td>
                </table>
                <br>
                <form method = "POST" id="form">
                    <input type= "hidden" id="documentID" value="${documentKind.id}">
                    <input id="name" name= "name" type="hidden" value="${documentKind.name}"/>
                    <input id="description" name = "description" type="hidden" value="${documentKind.description}"/>
                    <input id="picture" name = "picture" type="hidden" value="${documentKind.picture.url}"/>
                    <div id="docFields">
                        <c:forEach var="field" items="${documentKind.fields}">
                            <div id="field${field.id}" class="docField">
                                <script>loadField(${field.id})</script>
                            </div>
                        </c:forEach>
                    </div>
                </form>
                <button type="button" onclick="createField()">Добавить поле</button>
                <div class="buttonbar">
                    <button onclick="deleteDocumentKind(); window.location = '../admin';">Удалить</button>
                    <button onclick="window.location = '../admin'l">Отменить</button>
                    <button type="submit" form="form">Сохранить</button>
                </div>
            </td>
        </table>
    </body>
</html>