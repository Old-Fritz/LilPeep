
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


function addFormDocument() {
    let lastElem = $("#formDocuments").find(".formDocument").end();

    $.post("editForm", {
        type : 'addDocument',
        documentID : $("#selectedDocument").val(),
        name : $("#searchField").val(),
        formID : $("#formID").val()},
    function(responseText) {
        $('<div id="document'+responseText+'"></div>').insertAfter(lastElem);
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


