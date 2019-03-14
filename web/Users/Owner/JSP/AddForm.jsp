<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Форма</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Users/Owner/JS/FormsLists.js" type="text/javascript"></script>
    </head>
    <body>
        <input type="hidden" id="formID" value="${form.id}">
        <table>
            <jsp:include page="../includes/leftPanelOwner.jsp"/>
            <td class="content">
                <c:choose>
                    <c:when test="${isNewForm}">
                        <h1>Добавление формы</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Изменение формы</h1>
                    </c:otherwise>
                </c:choose>
				Название формы
				<br>
                <input type="text" value = "${form.name}" oninput="$('#name').val(this.value)"/><br>
				<br>
				<br>
				URL формы
				<br>
                <input type="text" value = "${form.url}" oninput="$('#url').val(this.value)"/><br>
				<br>
				<br>
                    Добавить документ
                    <br>
                    <table>
                        <tr>
                            <td>
                                <input type="hidden" id="selectedDocument" value="-1">
                                <input type="text" id="searchField" oninput="getSearchList(this.value, '')" autocomplete="off"/>
                            </td>
                            <td>
                                <button onclick="addFormDocument()">Добавить</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div id="searchList" class="searchlist"></div>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                <form id="docsForm" method="post" >
                    <input type="hidden" id = "name" name ="name" value="${form.name}"/><br>
                    <input type="hidden" id = "url" name ="url" value="${form.url}"/><br>
                    <div id="formDocuments">
                        <div class="formDocument"></div>
                        <c:forEach var="document" items="${documents}">
                            <div id="document${document.id}" class="formDocument">
                                <script>showDocument(${document.id});</script>
                            </div>
                        </c:forEach>
                    </div>
                </form>
                    <div class="buttonbar">
                        <button onclick="deleteForm(); window.location = '../owner'">Удалить</button>
                        <button onclick="window.location = '../owner'">Отменить</button>
                        <button type="submit" form="docsForm">Сохранить</button>
                    </div>
            </td>
        </table>
    </body>
</html>