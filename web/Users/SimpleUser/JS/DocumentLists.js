function getList(input, url) {
    $.get(url, {
        type : 'list',
        text : input
    }, function(responseText) {
        $('#list').html(responseText);
    });
}

$(document).ready(function() {
    getList("", $('#searchField').value);
});