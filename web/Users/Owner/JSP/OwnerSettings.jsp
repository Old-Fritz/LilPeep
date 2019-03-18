<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Настройки</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../Resources/JS/js.js" type="text/javascript"></script>
    </head>
    <body>
    <div id="rabbitWindow"></div>
        <table>
            <jsp:include page="../includes/leftPanelOwner.jsp"/>
            <td class="content">
                <form method = "POST" action="settings">
                    <h1>Настройки</h1>
                    Новый пароль
                    <br>
                    <input name="password" type="password"/>
                    <br>
                    <button type="submit">Сохранить</button>
                </form>
            </td>
        </table>
    </body>
</html>