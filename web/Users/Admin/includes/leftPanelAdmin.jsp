<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<td>
    <img class="menubutton" src="https://textseovip.ru/images/white-menu-icon.png" onclick="change()"/>
    <div id="panel" class="leftpanel">
        <div class="email" {email}>bibib-happens@habah.ru</div>
        <hr class="divider">
        <div class="button" onclick="window.location='documents'">Список документов</div>
        <br>
        <div class="button" onclick="window.location='addDocument'">Добавить документ</div>
        <br>
        <div class="button" onclick="window.location='owners'">Список владельцев</div>
        <br>
        <div class="button" onclick="window.location='complaints'">Поступившие жалобы</div>
        <br><br>
        <div class="button" onclick="window.location='../logout'">Выйти</div>
        <script>

            if(screen.width <= 854)
                document.getElementById("panel").style.display = 'none';
            function change() {
                if(document.getElementById("panel").style.display=='none') {
                    document.getElementById("panel").style.display = '';
                } else {
                    document.getElementById("panel").style.display = 'none';
                }
                return false;
            }
        </script>
    </div>
</td>