<html>
    <head>
        <meta charset="utf-8">
        <title>Запрос данных</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
    </head>
    <body class="form">
        <text>Сайт ${form.url} запрашивает доступ к следующим данным:</text>
        <br>
        <br>
        <text class="title">Название документа</text>
        <br>
        <li class="attr">Атрибут</li>
        <br>
        <br>
        <text>Не соглашайтесь на передачу данных если вы не доверяете сайту</text>
        <br>
        <br>
        <table align="right">
            <td>
                <button onClick="window.location='complaint'">Жалоба</button>
            </td>
            <td>
                <button onClick="window.history.back()">Отмена</button>
            </td>
            <td>
                <button {???????????}>Далее</button>
            </td>
        </table>
    </body>
</html>