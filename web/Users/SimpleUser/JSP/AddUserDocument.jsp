<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Добавление документа</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Resources/JS/list.js" type="text/javascript"></script>
    </head>
    <body>
        <table>
            <jsp:include page="../includes/leftPanelUser.jsp"/>
            <td class="content">
                <h1>Добавление документа</h1>
                <input type="text"  id='searchField' oninput="getList(this.value, 'addDocument')"/>
                <div id="list" class="searchresults"></div>
            </td>
        </table>
    </body>
</html>