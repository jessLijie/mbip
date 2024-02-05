<%@ page language="java" contentType="text/html;  charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="my.utm.ip.spring_jdbc.model.User" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
        <script type="text/javascript"
            src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
        <link rel="stylesheet" type="text/css"
            href="/static/css/Bill/download.css">
        <title>Water Bills-Download Report</title>
        <script src="/static/js/Bill/download.js"></script>
        <style>
            @media print {
                .navbar {
                    display: none !important;
                }
            }
        </style>
    </head>
    <%@include file="../navbar.jsp"%> 
    <body>
        <div class="outcontainer">
            <div class="button-container">
                <div class="backbtn"
                    onclick="window.location.href='/water/WaterHistory';">
                    <svg xmlns="http://www.w3.org/2000/svg" width="31"
                        height="29" viewBox="0 0 31 29" fill="none">
                        <ellipse cx="15.5" cy="13.5" rx="15.5" ry="13.5"
                            transform="matrix(-1 0 0 1 31 0)"
                            fill="#A7C3F9" />
                        <path
                            d="M7.49103 14.5607C6.90524 13.9749 6.90524 13.0251 7.49103 12.4393L17.037 2.8934C17.6228 2.30761 18.5725 2.30761 19.1583 2.8934C19.7441 3.47919 19.7441 4.42893 19.1583 5.01472L10.673 13.5L19.1583 21.9853C19.7441 22.5711 19.7441 23.5208 19.1583 24.1066C18.5725 24.6924 17.6228 24.6924 17.037 24.1066L7.49103 14.5607ZM22.4482 15H8.55169V12H22.4482V15Z"
                            fill="black" />
                        <ellipse cx="15.5" cy="14.5" rx="15.5" ry="14.5"
                            transform="matrix(-1 0 0 1 31 0)"
                            fill="#A7C3F9" />
                        <path
                            d="M5.93934 13.9393C5.35355 14.5251 5.35355 15.4749 5.93934 16.0607L15.4853 25.6066C16.0711 26.1924 17.0208 26.1924 17.6066 25.6066C18.1924 25.0208 18.1924 24.0711 17.6066 23.4853L9.12132 15L17.6066 6.51472C18.1924 5.92893 18.1924 4.97918 17.6066 4.3934C17.0208 3.80761 16.0711 3.80761 15.4853 4.3934L5.93934 13.9393ZM26 13.5L7 13.5V16.5L26 16.5V13.5Z"
                            fill="black" />
                    </svg>
                </div>
                <div class="d-flex justify-content-end">
                    <button type="button" id="downloadButton"
                        onclick="printReport()"
                        class="p-2 mx-5 rounded shadow text-center"
                        style="background-color: #A3FF6B; border:none;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16"
                            height="16" fill="currentColor"
                            class="bi bi-download" viewBox="0 0 16 16">
                            <path
                                d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5" />
                            <path
                                d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z" />
                        </svg>
                        <span class="ms-2"><b>Download PDF</b></span>
                    </button>
                </div>
            </div>
            <div class="page" size="A4">
                <div class="center-container">
                    <img src="/static/images/mbip.png" alt="MBIP Logo"
                        width="650" height="120">
                </div>
                <h3>Water Bills</h3>
        
                <table>

                    <tr>
                        <td><b>Name: </b></td>
                        <td>${user.fullname}</td>
                    </tr>
                    <tr>
                        <td><b>Email: </b></td>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <td><b>Phone Number: </b></td>
                        <td>${user.phone}</td>
                    </tr>
                    <tr>
                        <td style="vertical-align: top;"><b>Address: </b></td>
                        <td>${waterBill.address}</td>
                    </tr>
                    <tr>
                        <td><b>Period:</b></td>
                        <td>${period}</td>
                    </tr>
                    <tr>
                        <td><b>Prorata Factor:</b></td>
                        <td>1.03333</td>
                    </tr>
                    <tr>
                        <td><b>Current Consumption Value (kWh):</b></td>
                        <td><fmt:formatNumber value="${waterBill.currentConsumption}" pattern="0.00"/></td>
                    </tr>
                    <tr>
                        <td><b>Carbon Footprint (kg CO<sub>2</sub>): </b></td>
                        <td><fmt:formatNumber value="${waterBill.carbonFootprint}" pattern="0.00"/></td>
                    </tr>

                </table>
          
            </div>
        </div>
    </body>

</html>