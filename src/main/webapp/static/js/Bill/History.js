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

document.addEventListener("DOMContentLoaded", function() {
document.getElementById('leftArrowYear').addEventListener('click', function () {
    changeYear(-1);
});

document.getElementById('rightArrowYear').addEventListener('click', function () {
    changeYear(1);
});

function changeYear(year) {
 
    let yearElement = document.getElementById('yearDisplay');
    let currentYear = parseInt(yearElement.innerText, 10);
    let newYear = currentYear + year;
    yearElement.innerText = newYear.toString();
};

});

function applyFilter() {
    const year = document.getElementById("yearDisplay").innerText; // Get the displayed year
    
    let selectedMonths = [];
    const months = document.getElementsByName("month");
    months.forEach(month => {
        if (month.checked) {
            selectedMonths.push(month.value)
        }
    });
    const monthsString = selectedMonths.join(',');
   
    window.location.href = `/recycle/applyFilter?year=${year}&month=${monthsString}`;
}