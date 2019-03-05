function save() {
    createJsonResourceRequest(POST, "/client/save",
        createClientModel(),
        null,
        (xhr) => addRow(readModel(xhr)),
        (xhr) => closeDialog()
    );
}

function view(id) {
    createJsonResourceRequest(GET, "/client/find/" + id,
        null,
        null,
        (xhr) => openDialog(),
        (xhr) => bindModel(readModel(xhr))
    );
}

function edit() {
    var data = createClientModel();
    createJsonResourceRequest(PUT, "/client/edit",
        data,
        (xhr) => deleteRow(data.id),
        (xhr) => addRow(readModel(xhr)),
        (xhr) => closeDialog()
    );
}

function remove(id) {
    createJsonResourceRequest(DELETE, "/client/delete/" + id,
        null,
        null,
        (xhr) => deleteRow(id),
        null
    );
}

function bindModel(model) {
    document.getElementById("client_id").value = model.id;
    document.getElementById("client_name").value = model.name;
    document.getElementById("redirect_url").value = model.redirectUrl;
    document.getElementById("client_type").value = model.type;
}

function createClientModel() {
    var model = {
        "id": document.getElementById("client_id").value,
        "name": document.getElementById("client_name").value,
        "redirectUrl": document.getElementById("redirect_url").value,
        "type": document.getElementById("client_type").value,
    };

    return JSON.stringify(model);
}

function openDialog() {
    $("#dialog_client_form").modal();
}

function closeDialog() {
    $("#dialog_client_form").modal('toggle');
}


