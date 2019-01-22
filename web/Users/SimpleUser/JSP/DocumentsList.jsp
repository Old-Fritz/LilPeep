<%@ page import="java.util.List" %>
<%@ page import="DataBaseAcces.Entities.DocumentKind" %>

<%List<DocumentKind> documents = (List<DocumentKind>)request.getAttribute("documents");
for(DocumentKind document:documents){%>
    <%=document.getName()%>
    <button onclick="window.location = 'addedDocument?documentID=<%=document.getId()%>'">create</button>
    <br>
<%}%>

<div class="docitem">
    <table>
        <td>
            <image src="https://2ch.hk/b/arch/2018-11-26/src/187158673/15431788494610.jpg" alt="Случился Бибиб"/>
        </td>
        <td>
            <h1>Название</h1>
            Краткое описание
            <br>
            Есть даже место под вторую строку!
        </td>
    </table>
</div>