function insertAfter(el, referenceNode) {
    referenceNode.parentNode.insertBefore(el, referenceNode.nextSibling);
}

function loadField(fieldID){
    $.get("editDocument", {
            type : 'field',
        fieldID : fieldID},
        function(responseText) {
            $(""+documentID).html(responseText);
        });
}

function deleteDocumentKind()
{
    $.post("editDocument", {
            type : 'deleteDocument'},
        function(responseText) {
        });
    $("#document"+documentID).remove();
}

function deleteField(fieldID)
{
    $.post("editDocument", {
            fieldID : fieldID,
            type :"deleteField"},
        function(responseText) {

        });
}

function createField(){
    $.post("editForm", {
            type : 'createField'},
        function(responseText) {
            let newEl = document.createElement('div');
            newEl.id =  responseText;
            newEl.classList.add("docField");
            let node =  document.querySelectorAll(".docField").item(document.querySelectorAll(".docField").length-1);
            insertAfter(newEl, node);
            showDocument(responseText);
        });
}