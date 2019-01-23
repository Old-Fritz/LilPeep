<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<td class="leftpanel">
    <button onclick="change()">Меню</button>
    <div  id="panel">
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