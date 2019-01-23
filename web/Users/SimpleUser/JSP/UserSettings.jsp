<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Настройки</title>
    <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
</head>
<body>
<table>
    <jsp:include page="../includes/leftPanelUser.jsp"/>
    <td class="docedit">
        <form method = "POST" action="settings">
            Новый пароль
            <br>
            <input name="password" type="password"/>
            <br>
            <button type="submit">Сохранить</button>
        </form>
    </td>
</body>