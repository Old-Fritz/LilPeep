<html>
<head>
    <meta charset="utf-8">
    <title>Поступившие жалобы</title>
    <link rel="stylesheet" type="text/css" href="Resources/CSS/css.css">
</head>
<body>
<table>
    <td class="leftpanel">
        <jsp:include page="../includes/leftPanelAdmin.jsp"/>
    </td>
    <td class="content">
        <input type="text" oninput="getList(this.value)"/>
        {Сюда список из жалоб}
    </td>
    <script>getList("")</script>
</table>
</body>
</html>