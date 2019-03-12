<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<td class = "leftpaneltd">
    <img class="menubutton" src="https://textseovip.ru/images/white-menu-icon.png" onclick="change()"/>
    <div id="panel" class="leftpanel">

        <div class="email" {email}>bibib-happens@habah.ru</div>
        <hr class="divider">
        <div class="button" onclick="window.location='documents'">Список документов</div>
        <br>
        <div class="button" onclick="window.location='addDocument'">Добавить документ</div>
        <br>
        <div class="button" onclick="window.location='settings'">Настройки</div>
        <br><br>
        <div class="button" onclick="window.location='../logout'">Выйти</div>
        <script>
            function change() {
                if(screen.width <= 854)
                    document.querySelector(".leftpanel").style.display = 'none';
                if(document.querySelector(".leftpanel").style.display=='none') {
                    document.querySelector(".leftpanel").style.display = '';
                } else {
                    document.querySelector(".leftpanel").style.display = 'none';
                }
                return false;
            }
        </script>
    </div>
</td>
