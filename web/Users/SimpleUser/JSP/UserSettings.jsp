<html>
<head>
    <meta charset="utf-8">
    <title>Настройки</title>
    <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
</head>
<body>
<table>
    <jsp:include page="../Includes/leftPanel.html"/>
    <td class="docedit">
        <form method = "POST" action="user">
            Пароль
            <br>
            <input name="password" type="password"/>
            <br>
            <button type="submit">Сохранить</button>
        </form>
    </td>
</body>