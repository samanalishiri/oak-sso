document.getElementById("button_client_management").addEventListener("click",
    () => createViewPageRequest("/client/view.page",
        (xhr) => document.getElementById("content_page").innerHTML = xhr.responseText));
