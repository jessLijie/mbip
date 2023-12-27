const ctx = document.getElementById('doughnutChart');

new Chart(ctx, {
    type: 'polarArea',
    data: {
        labels: ['Paper', 'Plastic', 'Glass'],
        datasets: [{
            label: 'My First Dataset',
            data: [300, 50, 100],
            backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)', 'rgb(255, 205, 86)'],
            hoverOffset: 4
        }]
    },
    options: {
        responsive: true,
        scales: {
          r: {
              suggestedMin: 0,
              suggestedMax: 10 // Adjust the maximum scale value as needed
          }
      },
        plugins: {
            title: {
                display: true,
                text: 'Weight of recycled items',
                font: {
                    size: 16
                }
            }
        },
    }
});


