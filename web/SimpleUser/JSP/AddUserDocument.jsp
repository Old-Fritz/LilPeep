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

<input type="text" oninput="getList(this.value)"/>
<script>getList("")</script>

<div id="list">
</div>

<br><br><br>
<button onclick="window.location='addDocument'">Add document</button>
<button onclick="window.location='documents'">Documents</button>
<button onclick="window.location='settings'">Settings</button>
<button onclick="window.location='../logout'">Logout</button>