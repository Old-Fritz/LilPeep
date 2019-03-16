<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<table>
    <td>
        <input type="text" name="name${field.id}" value="${field.name}"/>
        <select name="fieldType${field.id}">
            <option ${field.fieldType.id==1? 'selected' : ' '} value="1">Строка</option>
            <option ${field.fieldType.id==2? 'selected' : ' '} value="2">Число</option>
            <option ${field.fieldType.id==3? 'selected' : ' '} value="3">Дата</option>
            <option ${field.fieldType.id==4? 'selected' : ' '} value="4">Утверждение</option>
        </select>
    </td>
    <td>
        <img class="deleteImg" onclick="deleteField(${field.id})" src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Crystal_button_cancel.svg/120px-Crystal_button_cancel.svg.png">
    </td>
</table>