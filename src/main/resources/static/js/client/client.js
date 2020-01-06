const INPUT_NAMES = ["client_id", "client_secret", "client_name", "redirect_uri", "access_token_validity_seconds", "client_type", "client_scope", "grant_type"];

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
    let model = JSON.parse(data);
    createJsonResourceRequest(PUT, "/client/edit",
        data,
        (xhr) => removeRow("client_grid", model.id),
        (xhr) => addModelToGrid(model),
        (xhr) => closeDialog()
    );
}

function findById(id) {
    createJsonResourceRequest(GET, "/client/find/" + id,
        null,
        null,
        (xhr) => bindModel(readModel(xhr)),
        null
    );
}

function remove(id) {
    createJsonResourceRequest(DELETE, "/client/remove/" + id,
        null,
        null,
        (xhr) => removeRow("client_grid", id),
        null
    );
}

function bindModel(model) {
    setValue("client_id",  model.id);
    setValue("client_secret",  model.clientSecret);
    setValue("client_name",  model.name);
    setValue("redirect_uri",  model.redirectUri);
    setValue("access_token_validity_seconds",  model.accessTokenValiditySeconds);
    initDropdownWithRefData(getTheElementById("client_type"), 'CLIENT_TYPE', model.type);
    initDropdownWithRefData(getTheElementById("client_scope"), 'CLIENT_SCOPE', model.scopes,
        {div: "scope_elements", arrayName: "scopes[]"});
    initDropdownWithRefData(getTheElementById("grant_type"), 'GRANT_TYPE', model.grantTypes,
        {div: "grant_type_elements", arrayName: "grantTypes[]"});

}

function createClientModel() {
    let model = {
        "id": getValue("client_id"),
        "clientSecret": getValue("client_secret"),
        "name": getValue("client_name"),
        "redirectUri": getValue("redirect_uri"),
        "accessTokenValiditySeconds": getValue("access_token_validity_seconds"),
        "type": getValue("client_type"),
        "scopes": readArray("scopes[]"),
        "grantTypes": readArray("grantTypes[]"),
    };

    return JSON.stringify(model);
}

function openNewDialog(model) {
    hiddenElements(["button_save", "button_edit", "button_close"])
    unhiddenElements(["button_save", "button_close"])

    openDialog();
}

function openViewDialog(model) {
    hiddenElements(["button_save", "button_edit", "button_close"])
    unhiddenElements(["button_close"])

    openDialog({defaultValue: false, disabled: true});
    findById(model.id);
}

function openEditableDialog(model) {
    hiddenElements(["button_save", "button_edit", "button_close"])
    unhiddenElements(["button_edit", "button_close"])

    openDialog({defaultValue: false});
    findById(model.id);
}


function openRemoveDialog(model) {
    remove(model.id);
}

function addModelToGrid(model, rowIndex = 0) {
    let cell = 0;

    let table = document.getElementById("client_grid").getElementsByTagName('tbody')[0];
    let row = table.insertRow(rowIndex);
    row.setAttribute('model_id', model.id);

    createTextCell(row, cell++, model.name);
    createTextCell(row, cell++, model.redirectUri);
    createTextCell(row, cell++, model.type);

    createButtonCell(row, cell++, 'View', 'button_view', () => openViewDialog(model));
    createButtonCell(row, cell++, 'Edit', 'button_edit', () => openEditableDialog(model));
    createButtonCell(row, cell++, 'Delete', 'button_delete', () => openRemoveDialog(model));
    createHiddenCell(row, cell++, model.id);
}

function destroyForm() {
    setDisabled(false);
    setValue("client_id",'');
    setValue("client_secret",'');
    setValue("client_name",'');
    setValue("redirect_uri",'');
    setValue("access_token_validity_seconds",'');
    setHtml("client_type","");
    setHtml("client_scope","");
    setHtml("grant_type","");
    setHtml("scope_elements","");
    setHtml("grant_type_elements","");
}

function resetForm() {
    setDisabled(false);
    setValue("client_id",'');
    setValue("client_secret",'');
    setValue("client_name",'');
    setValue("redirect_uri",'');
    setValue("access_token_validity_seconds",'');
    setHtml("scope_elements","");
    setHtml("grant_type_elements","");
    initDropdownWithRefData(getTheElementById("client_type"), 'CLIENT_TYPE');
    initDropdownWithRefData(getTheElementById("client_scope"), 'CLIENT_SCOPE');
    initDropdownWithRefData(getTheElementById("grant_type"), 'GRANT_TYPE');
}

function setDisabled(disabled) {
    if (disabled != undefined && disabled == true) {
        setAttributeToAll("disabled", "disabled", INPUT_NAMES);
    } else {
        removeAttributeFromAll("disabled", INPUT_NAMES);
    }
}

function openDialog(options) {
    $("#dialog_client_form").modal();

    if (options == undefined || options.defaultValue == undefined || options.defaultValue == true) {
        resetForm();
    }
    if (options != undefined && options.disabled != undefined) {
        setDisabled(options.disabled)
    }
}

function closeDialog() {
    destroyForm();
    $("#dialog_client_form").modal("toggle");
}
