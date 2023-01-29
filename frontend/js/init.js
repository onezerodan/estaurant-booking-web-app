import * as bookingService from './booking.js';

function book(){
    var bookButton = document.getElementById("bookButton");

    bookButton.addEventListener("click", e => {
    e.preventDefault();
    var e = document.getElementById("bookSelect");
    var value = e.value;
    if (!value) return;
    var c;
    bookingService.bookTable(value);
    //bookingService.showBookingInfo(value, confiramtionNumber);
   
    for (var i=0; i< e.length; i++) {
    if (e.options[i].value == value)
        e.remove(i);
    }

});
}

book();



