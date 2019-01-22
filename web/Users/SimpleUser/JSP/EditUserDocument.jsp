<html>
<head>
    <meta charset="utf-8">
    <title>Изменение документа</title>
    <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
</head>
<body>
<form method = "POST" action="/user/addedDocument">
    <table>
        <input type="text" oninput="getList(this.value)"/>
        <td class="content">
            <h1>Изменение документа</h1>
            <jsp:include page="../Includes/leftPanel.html"/>
            {Тут поля документа}
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