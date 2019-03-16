function insertAfter(el, referenceNode) {
    referenceNode.parentNode.insertBefore(el, referenceNode.nextSibling);
}

function loadField(fieldID){
    $.get("editDocument", {
        type : 'field',
        fieldID : fieldID,
            documentID : $("#documentID").val()},
        function(responseText) {
            $("#field"+fieldID).html(responseText);
        });
}

function deleteDocumentKind()
{
    $.post("editDocument", {
        documentID : $("#documentID").val(),
        type : 'deleteDocument'},
        function(responseText) {
        });
}

function deleteField(fieldID)
{
    $.post("editDocument", {
            fieldID : fieldID,
            documentID : $("#documentID").val(),
            type :"deleteField"},
        function(responseText) {
        });
    $("#field"+fieldID).remove();
}

function createField(){
    $.post("editDocument", {
            type : 'createField',
            documentID : $("#documentID").val()},
        function(responseText) {
            let newEl = document.createElement('div');
            newEl.id =  "field"+responseText;
            newEl.classList.add("docField");
            $("#docFields").append(newEl);
            loadField(responseText);
        });
}