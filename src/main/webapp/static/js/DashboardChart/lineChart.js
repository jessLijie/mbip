var carbonDataListJson = '${carbonDataListJson}';

if (carbonDataListJson.trim() !== '') {
    var carbonDataList = JSON.parse(carbonDataListJson);

    var labels = carbonDataList.map(entry => entry.month);
    var data = carbonDataList.map(entry => entry.totalCarbonData);

    var carbonFootprintChart = document.getElementById('carbonFootprintChart').getContext('2d');
    new Chart(carbonFootprintChart, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Total Carbon Footprint',
                data: data,
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    title: {
                        display: true,
                        text: 'Carbon (kg CO2)',
                        font: {
                            size: 10
                        }
                    },
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: 'Total Carbon Footprint by Month',
                    font: {
                        size: 16
                    }
                }
            }
        }
    });
} else {
    console.error('JSON data is empty or invalid.');
}