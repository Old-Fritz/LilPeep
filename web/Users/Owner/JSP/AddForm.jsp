<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Добавление формы</title>
        <link rel="stylesheet" type="text/css" href="../../Resources/CSS/css.css">
    </head>
    <body>
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
                            <input type="text" id="searchField" oninput="getSearchList(this.value, '')"/>
                            <div class="searchresults">
                                {здесь элементы поиска}
                                <br>
                            </div>
                        </tr>
                        <tr>
                            <button onclick="addFormDocument()">Добавить</button>
                        </tr>
                    </table>
                    <div id="searchList"></div>
                    <div id="formDocuments">
                        <c:forEach var="document" items="documents">
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