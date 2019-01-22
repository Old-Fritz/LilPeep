<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Список документов</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
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
    </head>
    <body>
    <table>
        <jsp:include page="/Includes/leftPanelAdmin.jsp"/>
        <td class="docedit">
            <h1>Список документов</h1>
        </td>
        <td class="content">
            <input type="text" oninput="getList(this.value)"/>
            {Сюда список из документов}
        </td>
        <script>getList("")</script>
    </table>

    </body>
</html>