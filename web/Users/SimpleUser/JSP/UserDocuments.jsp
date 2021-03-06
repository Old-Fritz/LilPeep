<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Список документов</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css?v=39">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Resources/JS/list.js" type="text/javascript"></script>
        <script src="../Resources/JS/js.js" type="text/javascript"></script>
    </head>
    <body>
    <div id="rabbitWindow"></div>
        <table>
            <jsp:include page="../includes/leftPanelUser.jsp"/>
            <td class="content">
                <h1>Список документов</h1>
                <input type="text" autocomplete="false" id='searchField' oninput="getList(this.value, 'documents')"/>
                    <div class="searchresults" id="list">
                    </div>
            </td>
        </table>
    </body>
</html>