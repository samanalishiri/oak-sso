function readModel(xhttp, name){
    return JSON.parse(xhttp.responseText)[name];
}

function readModel(xhttp) {
    return JSON.parse(xhttp.responseText);
}