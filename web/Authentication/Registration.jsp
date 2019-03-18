<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Регистарция</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
        <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="Resources/JS/js.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="rabbitWindow"></div>
        <table class="titlebar">
            <th>
                <span class="title" onClick="window.location='login'">Авторизация</span>
            </th>
            <th>
                <span selected class="title"><b>Регистрация</b></span>
            </th>
        </table>
        <br><br>
        <div class="loginmenu">
                <div>Логин</div>
                <input type="text" required oninput="$('#email').val(this.value)"/>
                <br><br>
                <div>Пароль</div>
                <input id="passwordOrig" type="password" oninput="$('#password').val(hashPassword(this.value))"/>
                <br><br>
                <div>Повторите пароль</div>
                <input type="password" oninput="$('#passwordRepeat').val(hashPassword(this.value))"/>

                <br><br>
            <form method = "POST" action="register">
                <div>Тип учётной записи</div>
                <select name="kindID">
                    <option selected value="1">Пользователь</option>
                    <option value="2">Владелец сайта</option>
                </select>
                <input type="text" id="email" name="email" required/>
                <input id="password" type="hidden" name="password" required/>
                <input id="passwordRepeat" type="hidden" name="passwordRepeat" required/>
                <br><br><br>
                <button type="submit" onclick="saveSecretKey(generateSecretKey($('#passwordOrig').val()))">Зарегистрироваться</button>
            </form>
        </div>
    </body>
</html>