const lineChart = document.getElementById('lineChart');

new Chart(lineChart, {
    type: 'line',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'Novermber', 'December'],
      datasets: [{
        label: 'Carbon Emission',
        data: [65, 59, 80, 81, 56, 55, 40, 80, 50, 65, 71, 62],
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
                    text: 'carbon (kg CO2)', 
                    font: {
                        size: 10 
                    }
                },
            }
        },
        plugins: {
            title: {
                display: true,
                text: 'Carbon Footprint over Months',
                font: {
                    size: 16
                }
            }
        }
    }
});
