<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/css/dashboard.css">
</head>
<body>
    
    <%@include file= "/WEB-INF/views/navbar.jsp" %>
    <div class="card-container">

    <!-- Electricity Consumption Card -->
    <div class="card" style="background-color: #f8d7da;">
        <div class="icon-info-container">
            <div class="icon-container">
                <i class="fas fa-bolt"></i>
            </div>            
            <div class="card-info">
                <h2>500 kWh</h2>
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
                <h2>1000 gallons</h2>
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
                <h2>25 kg CO2</h2>
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
                <h2>500 kg</h2>
                <p>Weight of Recycled Items</p>
            </div>
        </div>
    </div>
    </div>
    <div class= "graphBox">
        <div class="box" style="height: 350px; width: 350px" >
            <canvas id="doughnutChart"></canvas>
        </div>
        <div class="box" style="height: 350px; width: 1050px">
            <canvas id="lineChart" height="100"></canvas>
        </div>
    </div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js" integrity="sha512-L0Shl7nXXzIlBSUUPpxrokqq4ojqgZFQczTYlGjzONGTDAcLremjwaWv5A+EDLnxhQzY5xUZPWLOLqYRkY0Cbw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/DashboardChart/doughnutChart.js"></script>
<script src="/js/DashboardChart/lineChart.js"></script>
</html>