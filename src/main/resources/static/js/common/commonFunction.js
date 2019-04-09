function multiSelectDropdownAction(select, div, arrayName) {
    var option = select.options[select.selectedIndex];
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

function initDropDownWithRefData(select, group) {

    select.innerHTML = "";

    createJsonResourceRequest(GET, "/refdata/find/all/" + group,
        null,
        null,
        (xhr) => {
            var array = readModel(xhr);

            for (var i in array) {
                var option = document.createElement("option");
                option.value = array[i].id;
                option.appendChild(document.createTextNode(array[i].name));
                select.appendChild(option);
            }
        },
        null
    );
}