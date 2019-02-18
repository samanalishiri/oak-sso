document.getElementById("button_save").addEventListener("click", () => save());

function save() {
    postAjaxRequest("/client/save",
        createModel(),
        (xhttp) => addRow(readModel(xhttp, "data")),
        (xhttp) => closeDialog());
}

function view(id) {
    getAjaxRequest("/client/find/" + id, (xhttp) => bindModel(readModel(xhttp, "data")));
}

function edit() {
    postAjaxRequest("/client/edit", createModel(), (xhttp) => addRow(readModel(xhttp, "data")));
}

function remove(id) {
    deleteAjaxRequest("/client/delete/" + id, (xhttp) => deleteRow(id));
}

function bindModel(model){
    document.getElementById("client_id").value = model.id;
    document.getElementById("client_name").value = model.clientName;
    document.getElementById("redirect_url").value = model.redirectUrl;
    document.getElementById("client_type").value = model.clientType;
}

function createModel() {
    var model = {
        "id": document.getElementById("client_id").value,
        "clientName": document.getElementById("client_name").value,
        "redirectUrl": document.getElementById("redirect_url").value,
        "clientType": document.getElementById("client_type").value,
    };

    return JSON.stringify(model);
}

function openDialog() {
    var dialog = document.getElementById("dialog_client_form");
    dialog.setAttributeNode(document.createAttribute("open"));
}

function closeDialog() {
    var dialog = document.getElementById("dialog_client_form");
    dialog.removeAttribute("open")
}

