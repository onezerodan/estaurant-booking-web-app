async function getAvailableTables() {
   
    try {
        let res = await fetch("http://localhost:8080/api/getAvailable");
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderTables() {
    allTables = await getAvailableTables();
    result = Object.assign({}, allTables);
    let select = document.getElementById("bookSelect");
    for (let i = 0; i < allTables.length; i++){
        let option = document.createElement("option");
        option.value = allTables[i].id;
        option.text = allTables[i].id;
        select.appendChild(option);
    }
}

renderTables()