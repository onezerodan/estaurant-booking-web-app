

export async function bookTable(id) {
    const response = await fetch("http://localhost:8080/api/book", {
        method: "PUT",
        headers: { "Accept": "application/json", "Content-Type": "application/json" },
        body: JSON.stringify({
            id: id
        })
    });
    if (response.ok === true) {
        const table = await response.json();
        console.log("Table " + table.id + " successfully booked with confirmation number "
         + table.confirmationNumber);
         showBookingInfo(table.id, table.confirmationNumber);
    }
}
    

 function showBookingInfo(tableNumber, confirmationNumber) {
    var url = location.href;               //Save down the URL without hash.
    location.href = "#success";            //Go to the target element.
    var successPopup = document.getElementById("successPopup");  
    
    successPopup.replaceChildren();

    let title = document.createElement('h2');
    title.textContent = "Бронь создана";
    successPopup.appendChild(title);

    let closeButton = document.createElement('a');
    closeButton.innerHTML = '<a class="close" href="#">&times;</a>';
    successPopup.appendChild(closeButton);

    let tableNumberElement = document.createElement('div');
    tableNumberElement.textContent = "Номер стола: " + tableNumber;
    successPopup.appendChild(tableNumberElement);

    let space = document.createElement('br');
    successPopup.appendChild(space);

    let confirmationNumberElement = document.createElement('div');
    confirmationNumberElement.textContent = "Номер брони: " + confirmationNumber;
    successPopup.appendChild(confirmationNumberElement);

    history.replaceState(null,null,url);
}
