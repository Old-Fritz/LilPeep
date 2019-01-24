
function showSearchList()
{
    $.get("editForm", {
        type : 'searchList',
        text : $("#searchField").val(),
        formID : $("#formID").val()},
        function(responseText) {
        $('#searchList').html(responseText);
    });
}

function selectDocument(documentID, documentName)
{
    $("#selectedDocument").val(documentID);

    $("#searchField").val(documentName);
}

function showDocument(documentID)
{
    $.get("editForm", {
        type : 'formDocument',
            documentID : documentID,
            formID : $("#formID").val()},
        function(responseText) {
            $('#document'+documentID).html(responseText);
        });
}
function insertAfter(el, referenceNode) {
    referenceNode.parentNode.insertBefore(el, referenceNode.nextSibling);
}






function addFormDocument() {
    $.post("editForm", {
            type : 'addDocument',
            documentID : $("#selectedDocument").val(),
            name : $("#searchField").val(),
            formID : $("#formID").val()},
        function(responseText) {
            // example
            var newEl = document.createElement('div');
            newEl.id =  "document"+responseText;
            var node =  document.querySelectorAll(".formDocument").item(document.querySelectorAll(".formDocument").length-1)
            insertAfter(newEl, node);
            showDocument(responseText);
        });
}


function deleteDocument(documentID) {
    $.post("editForm", {
        type : 'deleteDocument',
        documentID : documentID,
        formID : $("#formID").val()},
        function(responseText) {
        });
    $("#document"+documentID).remove();
}

function deleteForm() {
    $.post("editForm", {
            type : 'deleteForm',
            formID : $("#formID").val()},
        function(responseText) {
        });
}

$(document).ready(function() {
    $("#searchField").on("blur", function () {
        $("#searchList").html("");
    });
    $("#searchField").on("input", function () {
        showSearchList();
    });
    $("#searchField").on("focus", function () {
        showSearchList();
    });
});


