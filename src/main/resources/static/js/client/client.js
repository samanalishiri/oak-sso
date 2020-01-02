function initClientGrid() {
    let table = document.getElementById("client_grid").getElementsByTagName('tbody')[0];
    table.innerHTML = "";

    createJsonResourceRequest(GET, "/client/find/all",
        null,
        null,
        (xhr) => initialGrid(readModel(xhr), addModelToGrid),
        null,
    );
}

function save() {
    createJsonResourceRequest(POST, "/client/save",
        createClientModel(),
        null,
        (xhr) => addModelToGrid(readModel(xhr)),
        (xhr) => closeDialog()
    );
}

function edit() {
    let data = createClientModel();
    createJsonResourceRequest(PUT, "/client/edit",
        data,
        (xhr) => deleteRow("client_grid", data.id),
        (xhr) => addModelToGrid(data),
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

function remove(id) {
    createJsonResourceRequest(DELETE, "/client/delete/" + id,
        null,
        null,
        (xhr) => deleteRow("client_grid", id),
        null
    );
}

function bindModel(model) {
    document.getElementById("client_id").value = model.id;
    document.getElementById("client_secret").value = model.clientSecret;
    document.getElementById("client_name").value = model.name;
    document.getElementById("redirect_uri").value = model.redirectUri;
    document.getElementById("access_token_validity_seconds").value = model.accessTokenValiditySeconds;

    initDropDownWithRefData(document.getElementById("client_type"), 'CLIENT_TYPE', model.id);
    document.getElementById("scopes").value = model.scopes;
    document.getElementById("grantTypes").value = model.grantTypes;
}

function createClientModel() {
    let scopes = [];
    for (e of document.getElementsByName("scopes[]").values()) {
        scopes.push(e.value);
    }

    let grantTypes = [];
    for (e of document.getElementsByName("grantTypes[]").values()) {
        grantTypes.push(e.value);
    }

    let model = {
        "id": document.getElementById("client_id").value,
        "clientSecret": document.getElementById("client_secret").value,
        "name": document.getElementById("client_name").value,
        "redirectUri": document.getElementById("redirect_uri").value,
        "accessTokenValiditySeconds": document.getElementById("access_token_validity_seconds").value,
        "type": document.getElementById("client_type").value,
        "scopes": scopes,
        "grantTypes": grantTypes,
    };

    return JSON.stringify(model);
}
function addModelToGrid(model, rowIndex = 0) {
    let cell = 0;

    let table = document.getElementById("client_grid").getElementsByTagName('tbody')[0];
    let row = table.insertRow(rowIndex);
    row.setAttribute('model_id', model.id);

    createTextCell(row, cell++, model.name);
    createTextCell(row, cell++, model.redirectUri);
    createTextCell(row, cell++, model.type);

    createButtonCell(row, cell++, 'View', 'button_view', () => view(model.id));
    createButtonCell(row, cell++, 'Edit', 'button_edit', () => view(model.id));
    createButtonCell(row, cell++, 'Delete', 'button_delete', () => remove(model.id));
    // createHiddenCell(row, cell++,'"' + model.id + '"');

}
function openDialog() {
    $("#dialog_client_form").modal();
}

function closeDialog() {
    $("#dialog_client_form").modal("toggle");
}


