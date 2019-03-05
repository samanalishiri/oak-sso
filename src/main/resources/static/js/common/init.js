function getToken() {

    var data = new FormData();
    data.append("grant_type", "client_credentials");
    data.append("client_secret", "spring-security-oauth2-read-write-client-password1234");
    data.append("client_id", "spring-security-oauth2-read-write-client");

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            sessionStorage.setItem("oauth_clientcredentials_token", this.responseText);
        }
    });

    xhr.open("POST", "/oauth/token");
    xhr.setRequestHeader("Authorization", 'Basic ' + btoa('spring-security-oauth2-read-write-client:spring-security-oauth2-read-write-client-password1234'));
    xhr.setRequestHeader("cache-control", "no-cache");
    xhr.send(data);
}