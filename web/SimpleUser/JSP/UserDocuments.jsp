<%@ page import="Entities.UserDocument" %>
<%@ page import="java.util.List" %>

<script>
    function getList(input) {
        let xhr = new XMLHttpRequest();
        let queryString = "type=list" +
            "&text="+ input;

        xhr.open("GET","documents?"+queryString, true);
        xhr.onload = function() {
            let list = document.querySelector("#list");
            list.innerHTML = xhr.response;
        };
        xhr.send(null);
    }
</script>

<input type="text" oninput="getList(this.value)"/>
<script>getList("")</script>

<div id="list"/>
