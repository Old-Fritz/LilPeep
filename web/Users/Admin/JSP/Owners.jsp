<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>Список владельцев</title>
        <link rel="stylesheet" type="text/css" href="../Resources/CSS/css.css">
    </head>
    <body>
        <table>
            <jsp:include page="../includes/leftPanelAdmin.jsp"/>
            <td class="content">
                <h1>Список владельцев</h1>
                <c:forEach var="owner" items="${owners}">
                    <table class="docitem">
                        <tr>
                            <td>
                                Почта
                            </td>
                            <td>
                                Кол-во форм
                            </td>
                            <td>
                                Кол-во жалоб
                            </td>
                        </tr>
                        <tr>
                            <td>
                                    ${owner.user.email}
                            </td>
                            <td>
                                    ${owner.formsCount}
                            </td>
                            <td>
                                    ${owner.complaintsCount}
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </td>
            <script>getList("")</script>
        </table>
    </body>
</html>