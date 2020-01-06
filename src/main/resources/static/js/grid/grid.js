function removeRow(tablename, id) {
    var table = document.getElementById(tablename);
    var row = table.querySelectorAll('[model_id="' + id + '"]')[0];
    row.parentNode.removeChild(row);
}

function initialGrid(models, target) {
    for (let i = 0; i < models.length; i++) {
        target(models[i]);
    }
}

function createTextCell(row, idx, val) {
    var cell = row.insertCell(idx);
    cell.innerHTML = "<label>" + val + "</label>"
}

function createButtonCell(row, idx, val, name, onclickfunc) {
    var button = row.insertCell(idx);
    button.innerHTML = "<td><input type='button' name=" + name + " value=" + val + " /></td>";
    document.getElementsByName(name)[0].onclick = onclickfunc;
    button.style = "text-align: center";
}

function createHiddenCell(row, idx, val) {
    var colUserId = row.insertCell(idx);
    colUserId.innerHTML = "<input type='hidden' value=" + val + " />";
    colUserId.style = "border-width: 0px";
}
