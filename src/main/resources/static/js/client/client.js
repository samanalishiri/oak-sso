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
    document.getElementById("scopes").value = model.scopes;
}

function createClientModel() {
    var scopes = [];
    for (e of document.getElementsByName("scopes[]").values()) {
        scopes.push(e.value);
    }

    var model = {
        "id": document.getElementById("client_id").value,
        "name": document.getElementById("client_name").value,
        "redirectUri": document.getElementById("redirect_uri").value,
        "type": document.getElementById("client_type").value,
        "scopes": scopes,
    };

    return JSON.stringify(model);
}

function openDialog() {
    $("#dialog_client_form").modal();
}

function closeDialog() {
    $("#dialog_client_form").modal("toggle");
}

function selectScope(select) {
    var option = select.options[select.selectedIndex];
    var parent = document.getElementById("scope_elements");

    var choices = parent.getElementsByTagName("input");
    for (var i = 0; i < choices.length; i++) {
        if (choices[i].value == option.value) {
            return;
        }
    }

    var row = document.createElement("div");
    row.classList.add("bg-light");

    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "scopes[]";
    input.value = option.value;
    row.appendChild(input);

    var text = document.createTextNode(option.firstChild.data);
    row.appendChild(text);


    var i = document.createElement("i");
    i.classList.add("fa");
    i.classList.add("fa-close");
    i.classList.add("red");
    i.setAttribute("onclick", "this.parentNode.parentNode.removeChild(this.parentNode);");
    row.appendChild(i);

    parent.appendChild(row);
}


