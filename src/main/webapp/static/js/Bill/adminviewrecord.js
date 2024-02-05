document.addEventListener("DOMContentLoaded", function () {
    const showFilterButton = document.getElementById("showFilter");
    const filterBox = document.getElementById("filterBox");

    showFilterButton.addEventListener("click", function () {
        if (filterBox.style.display === "none" || filterBox.style.display === "") {
            filterBox.style.display = "block";
        } else {
            filterBox.style.display = "none";
        }
    });
});



function applyFilter() {
    
    let selectedBills = [];
    const bill = document.getElementsByName("bill");
    bill.forEach(bill => {
        if (bill.checked) {
            selectedBills.push(bill.value)
        }
    });
    const billString = selectedBills.join(',');
   
    window.location.href = `/applyFilterBill?billString=${billString}`;
}