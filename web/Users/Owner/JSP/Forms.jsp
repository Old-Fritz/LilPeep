<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Список форм</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css?v=42">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Resources/JS/list.js" type="text/javascript"></script>
    </head>
    <body>
        <table>
            <jsp:include page="../includes/leftPanelOwner.jsp"/>
            <td class="content">
                <h1>Список форм</h1>
                <input type="text" autocomplete="false" id='searchField' oninput="getList(this.value, 'forms')"/>
                <div id="list" class="searchresults">
                </div>
            </td>
        </table>
    </body>
</html>