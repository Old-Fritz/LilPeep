<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<table>
    <td>
        <input type="text" name="name${field.id}" value="${field.name}"/>
    </td>
    <td>
        <img class="deleteImg" onclick="deleteField(${field.id})" src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Crystal_button_cancel.svg/120px-Crystal_button_cancel.svg.png">
    </td>
</table>