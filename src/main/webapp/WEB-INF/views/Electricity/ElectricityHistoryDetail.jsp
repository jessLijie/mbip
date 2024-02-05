<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>View Details</title>
            <link rel="stylesheet" type="text/css" href="/static/css/Bill/viewdetails.css">

        </head>
        <%@include file="/WEB-INF/views/navbar.jsp" %>

        <body>
            <div id="main">
                <div class="button-container">
                    <div class="backbtn" onclick="window.location.href='/electricity/ElectricityHistory';">
                        <svg xmlns="http://www.w3.org/2000/svg" width="31" height="29" viewBox="0 0 31 29" fill="none">
                            <ellipse cx="15.5" cy="13.5" rx="15.5" ry="13.5" transform="matrix(-1 0 0 1 31 0)"
                                fill="#A7C3F9" />
                            <path
                                d="M7.49103 14.5607C6.90524 13.9749 6.90524 13.0251 7.49103 12.4393L17.037 2.8934C17.6228 2.30761 18.5725 2.30761 19.1583 2.8934C19.7441 3.47919 19.7441 4.42893 19.1583 5.01472L10.673 13.5L19.1583 21.9853C19.7441 22.5711 19.7441 23.5208 19.1583 24.1066C18.5725 24.6924 17.6228 24.6924 17.037 24.1066L7.49103 14.5607ZM22.4482 15H8.55169V12H22.4482V15Z"
                                fill="black" />
                            <ellipse cx="15.5" cy="14.5" rx="15.5" ry="14.5" transform="matrix(-1 0 0 1 31 0)"
                                fill="#A7C3F9" />
                            <path
                                d="M5.93934 13.9393C5.35355 14.5251 5.35355 15.4749 5.93934 16.0607L15.4853 25.6066C16.0711 26.1924 17.0208 26.1924 17.6066 25.6066C18.1924 25.0208 18.1924 24.0711 17.6066 23.4853L9.12132 15L17.6066 6.51472C18.1924 5.92893 18.1924 4.97918 17.6066 4.3934C17.0208 3.80761 16.0711 3.80761 15.4853 4.3934L5.93934 13.9393ZM26 13.5L7 13.5V16.5L26 16.5V13.5Z"
                                fill="black" />
                        </svg>
                    </div>

                    <div class="historybtn">
                        <button type="button" onclick="window.location.href='/electricity/ElectricityHistory';">
                            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" viewBox="0 0 25 25"
                                fill="none">
                                <path
                                    d="M24.2188 12.0865C24.2311 18.7583 18.79 24.214 12.1182 24.2187C9.23657 24.2208 6.5897 23.2163 4.50967 21.5375C3.9688 21.101 3.92837 20.2904 4.41987 19.7989L4.97002 19.2487C5.39038 18.8284 6.06147 18.7824 6.5272 19.1519C8.05967 20.3679 9.99907 21.0938 12.1094 21.0938C17.0754 21.0938 21.0938 17.0747 21.0938 12.1094C21.0938 7.14331 17.0747 3.125 12.1094 3.125C9.72588 3.125 7.56108 4.05122 5.95371 5.56309L8.43193 8.04131C8.92412 8.5335 8.57554 9.375 7.87954 9.375H0.78125C0.349756 9.375 0 9.02524 0 8.59375V1.49546C0 0.799463 0.841504 0.450879 1.33369 0.943018L3.74443 3.35376C5.91841 1.27617 8.86484 0 12.1094 0C18.7896 0 24.2064 5.40918 24.2188 12.0865ZM15.3852 15.9333L15.8648 15.3167C16.2622 14.8058 16.1701 14.0695 15.6592 13.6722L13.6719 12.1264V7.03125C13.6719 6.38403 13.1472 5.85938 12.5 5.85938H11.7188C11.0715 5.85938 10.5469 6.38403 10.5469 7.03125V13.6548L13.7407 16.1389C14.2516 16.5362 14.9878 16.4442 15.3852 15.9333Z"
                                    fill="black" />
                            </svg>
                            <span>History</span>
                        </button>
                    </div>
                </div>

                <div class="viewhistory-container">
                    <h3>Electricity Bills</h3>
                    <table>
                        <tr>
                            <td style="vertical-align: top;"><b>Address: </b></td>
                            <td>${electricityBill.address}</td>
                        </tr>
                        
                            <td><b>Period:</b></td>
                            <td>${period}</td>
                        </tr>
                        <td><b>Prorata Factor:</b></td>
                        <td>1.00000</td>
                        </tr>
                        <tr>
                            <td><b>Current Consumption Value (kWh):</b></td>
                            <td><fmt:formatNumber value="${electricityBill.currentConsumption}" pattern="0.00"/></td>
                        </tr>
                    </table>
                    <hr>

                    <div class="carbon-footprint-section">
                        <b>Carbon Footprint (kg CO<sub>2</sub>):  </b>
                        <p class="ms-3"><fmt:formatNumber value="${electricityBill.carbonFootprint}" pattern="0.00"/> kgCO<sub>2</sub></p>
                    </div>

                </div>
            </div>
        </body>

        </html>