<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Авторизация</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
    </head>
    <body>
        <table class="titlebar">
            <th>
                <span selected class="title"><b>Авторизация</b></span>
            </th>
            <th>
                <span class="title" onClick="window.location='register'">Регистрация</span>
            </th>
        </table>
        <br><br>
        <div class="loginmenu">
            <form method = "POST" action="login">
                <div>Логин</div>
                <input type="text" name="email" required/>
                <br><br>
                <div>Пароль</div>
                <input type="text" type="password" name="password" required/>
                <br><br>
                <div>Тип учётной записи</div>
                <select name="kindID">
                    <option selected value="1">Пользователь</option>
                    <option value="2">Владелец сайта</option>
                    <option value="3">Администратор</option>
                </select>
                <br><br><br>
                <button type="submit" class>Войти</button>
            </form>
        </div>
    </body>
</div>
</body>
</html>