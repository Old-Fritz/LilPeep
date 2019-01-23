<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Список форм</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js"
                type="text/javascript"></script>
        <script src="../Resources/CSS/list.js" type="text/javascript"></script>
    </head>
    <body>
        <table>
            <jsp:include page="../includes/leftPanelOwner.jsp"/>
            <td class="docedit">
                <h1>Список форм</h1>
                <input type="text" id='searchField' oninput="getList(this.value, 'documents')"/>
                <div id="list"></div>
            </td>
        </table>
    </body>
</html>