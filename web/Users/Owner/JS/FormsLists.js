$("#searchField").on("blur", function () {
    $("#searchList").html("");
});
$("#searchField").on("input", function () {
    showSearchList();
});
$("#searchField").on("focus", function () {
    showSearchList();
});

function showSearchList()
{
    $.get("editForm", {
        type : 'searchList',
        text : $("#searchField"),
        formID : $("formID").value,},
        function(responseText) {
        $('#searchList').html(responseText);
    });
}

function selectDocument(documentID)
{
    $("#selectedDocument").value = documentID;

    $("#searchField").blur();
}

function addFormDocument() {
    let lastElem = $("#formDocuments").find(".formDocument").end();

    $.post("editForm", {
        type : 'addDocument',
        documentID : $("#selectedDocument").value,
        name : $("#searchField").value,
        formID : $("formID").value},
    function(responseText) {
        $('<div id="document'+responseText+'"></div>').insertAfter(lastElem);
        showDocument(responseText)
    });
}

function showDocument(documentID)
{
    $.get("editForm", {
        type : 'formDocument',
            documentID : documentID,
        formID : $("formID").value},
        function(responseText) {
            $('#element'+documentID).html(responseText);
        });
}

function deleteDocument(documentID) {
    $.post("editForm", {
        type : 'deleteDocument',
        documentID : documentID,
        formID : $("formID").value},
        function(responseText) {
        });
}

function deleteForm() {
    $.post("editForm", {
            type : 'deleteForm',
            formID : $("formID").value},
        function(responseText) {
        });
}