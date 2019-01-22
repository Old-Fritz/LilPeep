<html>
    <head>
        <meta charset="utf-8">
        <title>Добавление формы</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
    </head>
    <body>
        <table>
            <td class="docedit">
                <h1>Добавление формы</h1>
                <jsp:include page="../Includes/leftPanel.html"/>
            </td>
            <td class="content">
                <form>
                    <input type="text" name ="name"/><br>
                    <input type="text" name ="url"/><br>
                    Добавить документ
                    <br>
                    <table>
                        <tr>
                            <input type="text" oninput="getList(this.value)"/>
                        </tr>
                        <tr>
                            <button>Добавить</button>
                        </tr>
                    </table>
                    {Список из доков с атрибутами}
                    <div class="buttonbar">
                        <button {Надо как-то удалить док}>Удалить</button>
                        <button onclick="window.history.back()">Отменить</button>
                        <button type="submit">Сохранить</button>
                    </div>
                </form>
            </td>
            <script>getList("")</script>
        </table>
        <script>
            function getList(input) {
                let xhr = new XMLHttpRequest();
                let queryString = "type=list" +
                    "&text="+ input;
                xhr.open("GET","addDocument?"+queryString, true);
                xhr.onload = function() {
                    let list = document.querySelector("#list");
                    list.innerHTML = xhr.response;
                };
                xhr.send(null);
            }
        </script>
    </body>
</html>