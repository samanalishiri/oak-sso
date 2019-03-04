function deleteRow(id) {
    var table = document.getElementById("client_grid");
    var row = table.querySelectorAll('[model_id="' + id + '"]')[0];
    row.parentNode.removeChild(row);
}

function createTextCell(row, idx, val) {
    var cell = row.insertCell(idx);
    cell.innerHTML = "<label>" + val + "</label>"
}

function createButtonCell(row, idx, val, name, onclickfunc) {
    var button = row.insertCell(idx);
    button.innerHTML = "<td><input type='button' name=" + name + " value=" + val + " onclick=" + onclickfunc + " /></td>";
    button.style = "text-align: center";
}

function createHiddenCell(row, idx, val) {
    var colUserId = row.insertCell(idx);
    colUserId.innerHTML = "<input type='hidden' value=val />";
    colUserId.style = "border-width: 0px";
}

function addRow(model) {
    var idx = -1;

    var table = document.getElementById("client_grid").getElementsByTagName('tbody')[0];
    var row = table.insertRow(idx++);
    row.setAttribute('model_id', model.id);

    createTextCell(row, idx++, model.name);
    createTextCell(row, idx++, model.redirectUrl);
    createTextCell(row, idx++, model.type);

    createButtonCell(row, idx++, 'View', 'button_view', () => view(model.id));
    createButtonCell(row, idx++, 'Edit', 'button_edit', () => edit(model.id));
    createButtonCell(row, idx++, 'Delete', 'button_delete', () => remove(model.id));

    createHiddenCell(row, '"' + model.id + '"');
}
