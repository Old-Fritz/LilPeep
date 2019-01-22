<html>
<head>

</head>
<body>
<table class="titlebar">
    <th><span class="title" onClick="window.location='/login'">
        Авторизация
        </span></th>
    <th><span selected class="title">
        <b>Регистрация</b>
        </span></th>
</table>
<br>
<br>
<div class="loginmenu">
    <form method = "POST" action="register">
        <div>Логин</div>
        <input type="text" name="email" required/>
        <br>
        <br>
        <div>Пароль</div>
        <input type="text" name="password" required/>
        <br>
        <br>
        <div>Повторите пароль</div>
        <input type="text" name="passwordRepeat" required/>
        <br>
        <br>
        <div>Тип учётной записи</div>
        <select name="kindID">
            <option selected value="1">Пользователь</option>
            <option value="2">Владелец сайта</option>
        </select>
        <br>
        <br>
        <br>
        <button type="submit">Зарегистрироваться</button>
    </form>
</div>
</body>
</html>