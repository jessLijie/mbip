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

function monthNameToNumber(monthName) {
    const months = {
        'January': 1,
        'February': 2,
        'March': 3,
        'April': 4,
        'May': 5,
        'June': 6,
        'July': 7,
        'August': 8,
        'September': 9,
        'October': 10,
        'November': 11,
        'December': 12
    };

    return months[monthName];
}

function applyFilterWater() {
    const year = document.getElementById("yearDisplay").innerText;
    let selectedMonths = [];
    const months = document.getElementsByName("month");

    months.forEach(month => {
        if (month.checked) {
            selectedMonths.push(monthNameToNumber(month.value));
        }
    });

    const monthsString = selectedMonths.join(',');

    // Construct the filter URL
    const filterUrl = `/water/applyFilterWater?year=${year}&month=${monthsString}`;

    // Navigate to the filtered URL
    window.location.href = filterUrl;
}


