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

function applyFilterEvent() {
    const year = document.getElementById("yearDisplay").innerText; // Get the displayed year
    
    let selectedMonths = [];
    const months = document.getElementsByName("month");
    months.forEach(month => {
        if (month.checked) {
            selectedMonths.push(month.value)
        }
    });
    const monthsString = selectedMonths.join(',');
   
    window.location.href = `/event/applyFilterEvent?year=${year}&month=${monthsString}`;
}

// function monthNameToNumber(monthName) {
//     const months = {
//         'January': 1,
//         'February': 2,
//         'March': 3,
//         'April': 4,
//         'May': 5,
//         'June': 6,
//         'July': 7,
//         'August': 8,
//         'September': 9,
//         'October': 10,
//         'November': 11,
//         'December': 12
//     };

//     return months[monthName];
// }

// function applyFilterEvent() {
//     const year = document.getElementById("yearDisplay").innerText;
//     let selectedMonths = [];
//     const months = document.getElementsByName("month");

//     months.forEach(month => {
//         if (month.checked) {
//             selectedMonths.push(monthNameToNumber(month.value));
//         }
//     });

//     const monthsString = selectedMonths.join(',');

//     // Construct the filter URL
//     const filterUrl = `/event/applyFilterEvent?year=${year}&month=${monthsString}`;

//     // Navigate to the filtered URL
//     window.location.href = filterUrl;
function validateFileSize() {
    var fileInput = document.getElementById('event_img');  // Correct id here
    var maxFileSize = 50 * 1024; // 50KB in bytes

    if (fileInput.files.length > 0) {
        var fileSize = fileInput.files[0].size; // in bytes

        if (fileSize > maxFileSize) {
            alert('File size exceeds 50KB. Please choose a smaller file.');
            // Reset the file input
            fileInput.value = '';
        }
    }
}