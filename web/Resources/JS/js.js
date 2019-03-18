setInterval(checkRabbit, 1000);

function checkRabbit()
{
    $.get("/LilPeep/rabbit", {
    }, function(responseText) {
        showRabbit(responseText)
    });
}

function showRabbit(text) {
    if(text==="")
        return;

    let rabbitWindow =  document.getElementById("rabbitWindow")
    rabbitWindow.innerHTML=text;
    rabbitWindow.style.display = 'block';

    setTimeout(hideRabbit, 3000);
}

function hideRabbit()
{
    let rabbitWindow =  document.getElementById("rabbitWindow")
    rabbitWindow.innerHTML='';
    rabbitWindow.style.display = 'none';
}

function cryptStr(str)
{
    return str;
}

function decryptStr(str)
{
    return str;
}

function hashPassword(password)
{
    let hash = 0;
    for(let i = 0; i< password.length; i++)
    {
        hash+=hash*17+password.charCodeAt(i);
    }
    return hash;
}

function generateSecretKey(password) {
    let maxChar = 65535;
    let numbers = [17,23,31,41,47,59,67,73,89,101];
    let key = "";
    for(let i = 0;i<numbers.length;i++)
    {
        let hash = 0;
        for(let j = 0;j<password.length; j++)
            hash+=hash*numbers[i]+password.charCodeAt(j);
        hash%=maxChar;
        key+=String.fromCharCode(hash);
    }

    return key;
}

function saveSecretKey(key)
{
    sessionStorage.setItem("secretKey", key)
}

function loadSecretKey()
{
    return sessionStorage.getItem("secretKey");
}