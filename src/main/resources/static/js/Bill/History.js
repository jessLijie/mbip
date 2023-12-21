document.addEventListener("DOMContentLoaded", function() {
    const showFilterButton = document.getElementById("showFilter");
    const filterBox = document.getElementById("filterBox");

    showFilterButton.addEventListener("click", function() {
        if (filterBox.style.display === "none" || filterBox.style.display === "") {
            filterBox.style.display = "block";  
        } else {
            filterBox.style.display = "none";
        }
    });
});