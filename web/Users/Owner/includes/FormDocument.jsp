<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>


<div class="docitem">
    <table>
        <td>
            <image class="docimg" src="https://i.ytimg.com/vi/6Y3HE1_-lDQ/maxresdefault.jpg" alt="Случился Бибиб"/>
        </td>
        <td style="width: 70vw">
            <h>${document.documentKind.name}</h>
            <br>
            <c:forEach var="field" items="${document.formDocumentFields}">
                <input type="checkbox" name="${document.id}_${field.id}" ${field.checked?"checked":""}>${field.field.name}</input>
                <br>
            </c:forEach>
        </td>
        <td>
            <button onclick="deleteDocument(${document.id})" style="margin-top: 50%;">Удалить</button>
        </td>
    </table>
</div>

