<%@ page import="java.util.List" %>
<%@ page import="DataBaseAcces.Entities.FormDocumentField" %>
<%@ page import="DataBaseAcces.Entities.FormDocument" %>

<script>
    function deleteDocument(id) {
        let xhr = new XMLHttpRequest();

        let body = "documentID="+encodeURIComponent(id) +
            "&formID=" + encodeURIComponent(<%=request.getParameter("formID")%>)+
            "&type="+encodeURIComponent("document");

        xhr.open("DELETE","owner/editForm", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            window.location="editForm?formID="+ <%=request.getParameter("formID")%>;
        };
        xhr.send(body);
    }
</script>

<%  FormDocument document = (FormDocument)request.getAttribute("document"); %>
<h><%=document.getDocumentKind().getName()%></h>
<button onclick="deleteDocument(<%=document.getId()%>)">Удалить</button>
<%  List<FormDocumentField> formDocumentFields = document.getFormDocumentFields();
    for(FormDocumentField field : formDocumentFields){
        String checked = field.isChecked() ? "checked" :"";
        %>
        <input type="checkbox" name="<%=document.getId()+"_"+field.getId()%>" <%=checked%>><%=field.getField().getName()%></input>
        <br>
<%  }%>
