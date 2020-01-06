function multiSelectDropdownAction(select, div, arrayName, value) {

    var option;

    option = (value == undefined || value == null) ? select.options[select.selectedIndex] : getOptionByValue(select, value);

    var parent = document.getElementById(div);

    var choices = parent.getElementsByTagName("input");
    for (var i = 0; i < choices.length; i++) {
        if (choices[i].value == option.value) {
            return;
        }
    }

    var row = document.createElement("div");
    row.classList.add("row");
    row.classList.add("bg-light");

    var input = document.createElement("input");
    input.type = "hidden";
    input.name = arrayName;
    input.value = option.value;
    row.appendChild(input);

    var textHolder = document.createElement("div");
    textHolder.classList.add("col-lg-11");
    var text = document.createTextNode(option.firstChild.data);
    textHolder.appendChild(text);
    row.appendChild(textHolder);

    var i = document.createElement("i");
    i.classList.add("fa");
    i.classList.add("fa-close");
    i.classList.add("red");
    i.setAttribute("onclick", "this.parentNode.parentNode.removeChild(this.parentNode);");
    row.appendChild(i);

    parent.appendChild(row);
}

function initDropdownWithArray(select, array, defaultValue, multiselect) {

    var option = document.createElement("option");
    option.value = -1;
    option.appendChild(document.createTextNode("choose"));
    select.appendChild(option);

    for (var i in array) {
        var option = document.createElement("option");
        option.value = array[i].id;
        option.appendChild(document.createTextNode(array[i].name));

        if ((multiselect == undefined || multiselect == null) && (defaultValue != undefined && defaultValue != null)) {
            option.selected = true;
        }

        select.appendChild(option);
    }

    if (multiselect != undefined || multiselect != null) {

        for (var i in defaultValue) {
            multiSelectDropdownAction(select, multiselect.div, multiselect.arrayName, defaultValue[i]);
        }
    }

}

function initDropdownWithRefData(select, group, defaultValue, multiselect) {

    select.innerHTML = "";

    createJsonResourceRequest(GET, "/refdata/find/all/" + group,
        null,
        null,
        (xhr) => initDropdownWithArray(select, readModel(xhr), defaultValue, multiselect),
        null
    );
}

function selectOptionByValue(select, value) {
    var options = select.options;
    for (var i = 0; i < options.length; i++) {
        if (value != undefined && value != null && options[i].value == value) options[i].selected = true;
    }
}

function getOptionByValue(select, value) {
    var options = select.options;
    for (var i = 0; i < options.length; i++) {
        if (value != undefined && value != null && options[i].value == value) return options[i];
    }
}

function selectOptionByName(select, name) {
    var options = select.options;
    for (var i = 0; i < options.length; i++) {
        if (name != undefined && name != null && options[i].name == name) options[i].selected = true;
    }
}

function readArray(arrayName) {
    let array = [];
    for (e of document.getElementsByName(arrayName).values()) {
        array.push(e.value);
    }
    return array;
}

function setAttributeToAll(att, val, identities) {
    for(i in identities){
        document.getElementById(identities[i]).setAttribute(att, val);
    }
}

function removeAttributeFromAll(att, identities) {
    for(i in identities){
        document.getElementById(identities[i]).removeAttribute(att);
    }
}

function hiddenElements(identities){
    setAttributeToAll("hidden", true, identities);

}
function unhiddenElements(identities){
    removeAttributeFromAll("hidden", identities);

}

function getTheElementById(id) {
    return document.getElementById(id);
}

function setValue(id, val) {
    return document.getElementById(id).value = val;
}

function setHtml(id, val) {
    return document.getElementById(id).innerHTML = val;
}

function getValue(id) {
    return document.getElementById(id).value;
}

