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
    document.getElementById("redirect_uri").value = model.redirectUrl;
    document.getElementById("access_token_validity_seconds").value = model.accessTokenValiditySeconds;
    document.getElementById("scopes").value = model.scopes;
    document.getElementById("grantTypes").value = model.grantTypes;
}

function createClientModel() {
    var scopes = [];
    for (e of document.getElementsByName("scopes[]").values()) {
        scopes.push(e.value);
    }

    var grantTypes = [];
    for (e of document.getElementsByName("grantTypes[]").values()) {
        grantTypes.push(e.value);
    }

    var model = {
        "id": document.getElementById("client_id").value,
        "name": document.getElementById("client_name").value,
        "redirectUri": document.getElementById("redirect_uri").value,
        "accessTokenValiditySeconds": document.getElementById("access_token_validity_seconds").value,
        "type": document.getElementById("client_type").value,
        "scopes": scopes,
        "grantTypes": grantTypes,
    };

    return JSON.stringify(model);
}

function openDialog() {
    $("#dialog_client_form").modal();
}

function closeDialog() {
    $("#dialog_client_form").modal("toggle");
}


