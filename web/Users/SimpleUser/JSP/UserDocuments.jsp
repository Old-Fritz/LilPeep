<html>
    <head>
        <meta charset="utf-8">
        <title>Список документов</title>
        <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
    </head>
    <body>
        <table>
            <td class="docedit">
                <jsp:include page="../Includes/leftPanel.html"/>
            </td>
            <td class="content">
                <h1>Список документов</h1>
                <input type="text" oninput="getList(this.value)"/>
                {Сюда список из документов}
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