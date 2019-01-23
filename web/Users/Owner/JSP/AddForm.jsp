<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Добавление формы</title>
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
                <h1>Добавление формы</h1>
                <form method="POST">
                    <input type="text" name ="name"/><br>
                    <input type="text" name ="url"/><br>

                    Добавить документ
                    <br>
                    <table>
                        <tr>
                            <input type="hidden" id="selectedDocument" value="-1">
                            <input type="text" id="searchField" oninput="showSearchList(this.value, '')"/>
                        </tr>
                        <tr>
                            <button onclick="addFormDocument()">Добавить</button>
                        </tr>
                    </table>
                    <div id="searchList"></div>
                    <div id="formDocuments">
                        <div class="formDocument"></div>
                        <c:forEach var="document" items="${documents}">
                            <div id="document${document.id}" class="formDocument">
                                <script>showDocument(${document.id});</script>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="buttonbar">
                        <button onclick="deleteForm()">Удалить</button>
                        <button onclick="window.history.back()">Отменить</button>
                        <button type="submit">Сохранить</button>
                    </div>
                </form>
            </td>
        </table>
    </body>
</html>