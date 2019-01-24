<html>
    <head>
        <meta charset="utf-8">
        <title>Изменение документа</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
    </head>
    <body>
        <form method = "POST" action="/user/addedDocument">
            <table>
                <jsp:include page="../includes/leftPanelAdmin.jsp"/>
                <td class="content">
                    <h1>Изменение документа</h1>
                    <table  class="docitem" editable>
                        <td>
                            Название:<br>
                            <input type="text"/>
                            <br>
                            <br>
                            Описание:<br>
                            <input type="text"/>
                            <br>
                            <br>
                            URL картинки:<br>
                            <input id="thInput" type="text"/>
                            <button type="button" onclick="document.getElementById('thumbnail').src = document.getElementById('thInput').value">Обновить миниатюру</button>
                            <br>
                            <br>
                        </td>
                        <td>
                            <image id="thumbnail" class="docimg" alt="Здесь могла быть ваша реклама"/>
                        </td>
                    </table>
                    <br>
                    {Тут поля документа AdminField.html}
                </td>

                <script>getList("")</script>
            </table>
            <div class="buttonbar">
                <button {Надо как-то удалить док}>Удалить</button>
                <button onclick="window.history.back()">Отменить</button>
                <button type="submit">Сохранить</button>
            </div>
        </form>

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