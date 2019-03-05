
function addRow(model) {
    var cell = 0;

    var table = document.getElementById("client_grid").getElementsByTagName('tbody')[0];
    var row = table.insertRow(0);
    row.setAttribute('model_id', model.id);

    createTextCell(row, cell++, model.name);
    createTextCell(row, cell++, model.redirectUrl);
    createTextCell(row, cell++, model.type);

    createButtonCell(row, cell++, 'View', 'button_view', () => view(model.id));
    createButtonCell(row, cell++, 'Edit', 'button_edit', () => edit(model.id));
    createButtonCell(row, cell++, 'Delete', 'button_delete', () => remove(model.id));

    createHiddenCell(row, '"' + model.id + '"');
}
