
function save() {
    postAjaxRequest("/client/save",
        createClientModel(),
        (xhttp) => addRow(readModel(xhttp, "data")),
        (xhttp) => closeDialog());
}

function view(id) {
    getAjaxRequest("/client/find/" + id, (xhttp) => bindModel(readModel(xhttp, "data")));
}

function edit() {
    postAjaxRequest("/client/edit", createClientModel(), (xhttp) => addRow(readModel(xhttp, "data")));
}

function remove(id) {
    deleteAjaxRequest("/client/delete/" + id, (xhttp) => deleteRow(id));
}

function bindModel(model){
    document.getElementById("client_id").value = model.id;
    document.getElementById("client_name").value = model.name;
    document.getElementById("redirect_url").value = model.redirectUrl;
    document.getElementById("client_type").value = model.type;
}

function createClientModel() {
    var client = {
        "id": document.getElementById("client_id").value,
        "name": document.getElementById("client_name").value,
        "redirectUrl": document.getElementById("redirect_url").value,
        "type": document.getElementById("client_type").value,
    };

    return JSON.stringify(client);
}

function openDialog() {
    $("#dialog_client_form").modal();
}


