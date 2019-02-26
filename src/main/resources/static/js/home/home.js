document.getElementById("button_register_client").addEventListener("click", () => goToClientPage());

function viewClientPage(data) {
    var data = null;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {

        }
    });
    xhr.open("GET", "/client/view.page");
    xhr.setRequestHeader("Authorization", "Bearer " + JSON.parse(data).access_token);
    xhr.setRequestHeader("cache-control", "no-cache");

    xhr.send(data);
}

function goToClientPage() {

    var data = new FormData();
    data.append("grant_type", "client_credentials");
    data.append("client_secret", "spring-security-oauth2-read-write-client-password1234");
    // data.append("username", "admin");
    // data.append("password", "admin1234");
    data.append("client_id", "spring-security-oauth2-read-write-client");

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            viewClientPage(this.responseText);
            console.log(JSON.parse(this.responseText).access_token);
        }
    });

    xhr.open("POST", "/oauth/token");
    xhr.setRequestHeader("Authorization", 'Basic ' + btoa('spring-security-oauth2-read-write-client:spring-security-oauth2-read-write-client-password1234'));
    xhr.setRequestHeader("cache-control", "no-cache");

    xhr.send(data);
}