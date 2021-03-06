<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Авторизация</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="Resources/JS/js.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="rabbitWindow"></div>
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

            <div>Логин</div>
            <input type="text" oninput="$('#email').val(this.value)"/>
            <br><br>
            <div>Пароль</div>
            <input type="password" name="password" oninput="$('#password').val(hashPassword(this.value))"/>
            <br><br>

            <form method = "POST" action="login">
                <input type="hidden" id="email" name="email" required/>
                <input type="hidden" id="password" name="password" required/>
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