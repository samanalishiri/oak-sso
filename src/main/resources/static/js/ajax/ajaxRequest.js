const POST = "POST";
const PUT = "PUT";
const GET = "GET";
const DELETE = "DELETE";


function createJsonResourceRequest(verb, url, data, beforeSuccess, success, afterSuccess) {
    let token = sessionStorage.getItem("oauth_clientcredentials_token");

    var xhr = new XMLHttpRequest();
    xhr.open(verb, url);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Authorization", "Bearer " + JSON.parse(token).access_token);
    xhr.setRequestHeader("cache-control", "no-cache");
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            if (beforeSuccess != undefined && beforeSuccess != null) beforeSuccess(xhr);
            if (success != undefined && success != null) success(xhr);
            if (afterSuccess != undefined && afterSuccess != null) afterSuccess(xhr);
        }
    });
    xhr.send(data);
}

function createViewPageRequest(url, success) {
    let token = sessionStorage.getItem("oauth_clientcredentials_token");

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.open("GET", url);
    xhr.setRequestHeader("Authorization", "Bearer " + JSON.parse(token).access_token);
    xhr.setRequestHeader("cache-control", "no-cache");
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            if (success != null) success(xhr);
        }
    });

    xhr.send();
}
