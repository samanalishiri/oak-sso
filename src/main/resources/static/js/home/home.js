document.getElementById("button_register_client").addEventListener("click",
    () => createViewPageRequest("/client/view.page",
        (xhr) => document.getElementById("content_page").innerHTML = xhr.responseText));
