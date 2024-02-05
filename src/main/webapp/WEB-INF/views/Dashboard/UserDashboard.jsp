<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="../static/css/dashboard.css">
</head>
<body style="background-color: #ccf3ea;">
    
    <%@include file= "/WEB-INF/views/navbar.jsp" %>
    <%@ page session="true" %>
    <div class="name-container">
        <h1>Hello, ${name}</h1>
    </div>

    <div class="card-container">
    <!-- Electricity Consumption Card -->
    <div class="card" style="background-color: #f8d7da;">
        <div class="icon-info-container">
            <div class="icon-container">
                <i class="fas fa-bolt"></i>
            </div>            
            <div class="card-info">
                <h2>${totalElectricity} kWh</h2>
                <p>Electricity Consumption</p>
            </div>
        </div>
    </div>

    <!-- Water Consumption Card -->
    <div class="card" style="background-color: #d4edda;">
        <div class="icon-info-container">
            <div class="icon-container">
                <i class="fas fa-tint"></i>
            </div>  
            <div class="card-info">
                <h2>${totalWater} gallons</h2>
                <p>Water Consumption</p>
            </div>
        </div>
    </div>

    <!-- Carbon Footprint Card -->
    <div class="card" style="background-color: #cce5ff;">
        <div class="icon-info-container">
            <div class="icon-container">
                <i class="fas fa-leaf"></i>
            </div>  
            <div class="card-info">
                <h2>${totalCarbon} kg CO2</h2>
                <p>Carbon Footprint</p>
            </div>
        </div>
    </div>

    <!-- Weight of Recycled Items Card -->
    <div class="card" style="background-color: #d6d8d9;">
        <div class="icon-info-container">
            <div class="icon-container">
                <i class="fas fa-recycle"></i>
            </div>  
            <div class="card-info">
                <h2>${recycleWeight} kg</h2>
                <p>Weight of Recycled Items</p>
            </div>
        </div>
    </div>
    </div>
    <div class= "graphBox">
        <div class="box" style="height: 350px; width: 350px" >
            <canvas id="pieChart"></canvas>
        </div>
        <div class="box" style="height: 350px; width: 1050px">
            <canvas id="carbonFootprintChart" height="100"></canvas>

        </div>
    </div>
</body>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js" integrity="sha512-L0Shl7nXXzIlBSUUPpxrokqq4ojqgZFQczTYlGjzONGTDAcLremjwaWv5A+EDLnxhQzY5xUZPWLOLqYRkY0Cbw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
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
                        text: 'Total Carbon Footprint based on Monthly Electricity and Water Consumption',
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
</script>
<script>
    var contributionListJson = '${contributionList}';
    var contributionList = JSON.parse(contributionListJson);

    var electricityPercentage = contributionList[0].electricityPercentage;
    var waterPercentage = contributionList[0].waterPercentage;

    var pieChart = document.getElementById('pieChart').getContext('2d');
    new Chart(pieChart, {
        type: 'pie',
        data: {
            labels: ['Electricity', 'Water'],
            datasets: [{
                data: [electricityPercentage, waterPercentage],
                backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)'],
            }]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Percentage Contribution to Total Carbon Footprint',
                    font: {
                        size: 16
                    }
                }
            }
        }
    });
</script>

</html>