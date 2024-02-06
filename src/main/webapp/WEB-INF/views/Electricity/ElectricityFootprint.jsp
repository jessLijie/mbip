<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Insert Electricity Consumption</title>
        <link rel="stylesheet" type="text/css">
        <style>

            .bg-white svg {
                width: 35px;
                height: 40px;
                fill: #D0D40D;
                text-align:  center;
            }
        </style>
    </head>
    <%@include file="../navbar.jsp"%> 

    <body style="background-color: #CCF3EA">
        <div class="container-fluid"><!--button container-->
            <div class="row m-5">
                <div class="col-auto"
                    onclick="window.location.href='/electricity/InsertElectricityConsumption';">
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

                <div class="col-md-9 ms-auto text-end">
                    <button type="button" class="px-4 p-2 rounded shadow"
                        style="background-color: #A7C3F9; border:none;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25"
                            height="25" viewBox="0 0 25 25" fill="none">
                            <path
                                d="M24.2188 12.0865C24.2311 18.7583 18.79 24.214 12.1182 24.2187C9.23657 24.2208 6.5897 23.2163 4.50967 21.5375C3.9688 21.101 3.92837 20.2904 4.41987 19.7989L4.97002 19.2487C5.39038 18.8284 6.06147 18.7824 6.5272 19.1519C8.05967 20.3679 9.99907 21.0938 12.1094 21.0938C17.0754 21.0938 21.0938 17.0747 21.0938 12.1094C21.0938 7.14331 17.0747 3.125 12.1094 3.125C9.72588 3.125 7.56108 4.05122 5.95371 5.56309L8.43193 8.04131C8.92412 8.5335 8.57554 9.375 7.87954 9.375H0.78125C0.349756 9.375 0 9.02524 0 8.59375V1.49546C0 0.799463 0.841504 0.450879 1.33369 0.943018L3.74443 3.35376C5.91841 1.27617 8.86484 0 12.1094 0C18.7896 0 24.2064 5.40918 24.2188 12.0865ZM15.3852 15.9333L15.8648 15.3167C16.2622 14.8058 16.1701 14.0695 15.6592 13.6722L13.6719 12.1264V7.03125C13.6719 6.38403 13.1472 5.85938 12.5 5.85938H11.7188C11.0715 5.85938 10.5469 6.38403 10.5469 7.03125V13.6548L13.7407 16.1389C14.2516 16.5362 14.9878 16.4442 15.3852 15.9333Z"
                                fill="black" />
                        </svg>
                        <a href="/electricity/ElectricityHistory"
                            style="text-decoration: none; color: black"><span
                                class="ms-2"><b>History</b></span></a>
                    </button>

                    <button type="button" class="px-4 p-2 ms-5 rounded shadow"
                        style="background-color: #03C043; border:none;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25"
                            height="25" fill="currentColor"
                            class="bi bi-file-earmark-plus" viewBox="0 0 16 16">
                            <path
                                d="M8 6.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V11a.5.5 0 0 1-1 0V9.5H6a.5.5 0 0 1 0-1h1.5V7a.5.5 0 0 1 .5-.5" />
                            <path
                                d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5z" />
                        </svg>
                        <a href="/electricity/InsertElectricityConsumption"
                            style="text-decoration: none; color: black"><span
                                class="ms-2"><b>Add Bills</b></span></a>
                    </button>
                </div>

            </div>
        </div>

        <div class="container mt-2" style="width: 77%">
            <div class="row m-5">
                <!--left side-->
                <div
                    class="col-md-4 d-flex flex-column justify-content-between">
                    <div
                        class="bg-white p-4 px-5 rounded shadow m-3 text-center">
                        <!--current water consumption section-->
                        <svg xmlns="http://www.w3.org/2000/svg" width="35"
                            height="40" viewBox="0 0 25 38" fill="none">
                            <path
                                d="M23.1256 11.875H14.11L17.4381 2.24141C17.7506 1.11328 16.8522 0 15.6256 0H4.37578C3.43829 0 2.64142 0.660547 2.51642 1.54375L0.0164512 19.3562C-0.131985 20.425 0.743005 21.375 1.8758 21.375H11.1491L7.54761 35.8105C7.26637 36.9387 8.17261 38 9.36791 38C10.0241 38 10.6491 37.6734 10.9929 37.1094L24.7427 14.5469C25.4693 13.3668 24.5709 11.875 23.1256 11.875Z"
                                fill="#D0D40D" />
                        </svg><br>
                        <span class="mt-2">Total electricity Consumption (kWh)
                        </span>
                        <h1 class="mt-auto mb-0"><fmt:formatNumber value="${sumConsumption}" pattern="0.00"/></h1>

                    </div>

                    <div
                        class="bg-white p-4 px-5 rounded shadow m-3 text-center"><!--carbon footprint section-->
                        <img src="/static/images/carbonFootprint.png"
                            alt="Carbon Footprint" width="65" height="65">
                        <div
                            class="d-flex flex-column align-items-center justify-content-center">
                            <span class="mt-2">Total Carbon Footprint (kg
                                CO<sub>2</sub>)</span>
                            <h1 class="mt-auto mb-0"><b>${sumCarbonFootprint}</b></h1>
                        </div>
                    </div>

                    <button type="button"
                        class="p-2 mx-5 m-3 rounded shadow text-center"
                        style="background-color: #A3FF6B; border:none;"><!--download report section-->
                        <svg xmlns="http://www.w3.org/2000/svg" width="16"
                            height="16" fill="currentColor"
                            class="bi bi-download" viewBox="0 0 16 16">
                            <path
                                d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5" />
                            <path
                                d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z" />
                        </svg>
                        <a href="/electricity/ElectricityDownloadReport?billId=${electricityBill.id}"
                            style="text-decoration: none; color: black"><span
                                class="ms-2"><b>Download
                                    Report</b></span></a>
                    </button>
                </div>
            
            <!--right side-->
            <div class="col-md-8">
                <div
                    class="bg-white p-4 px-5 rounded shadow m-3"><!--details section-->
                    <h3 class="text-center mb-4 mt-2"><b>Electricity
                            Record</b></h3>
                    <div class="row mb-4">
                        <div class="col-md-6"><b>Address:</b></div>
                        <div class="col-md-6">${electricityBill.address}</div>
                    </div>

                    <div class="row mb-4">
                        <div class="col-md-6"><b>Period:</b></div>
                        <div class="col-md-6">${period}</div>
                    </div>

                    <div class="row mb-4">
                        <div class="col-md-6"><b>Prorata Factor:</b></div>
                        <div class="col-md-6">1.00000</div>
                    </div>

                    <div class="row mb-5">
                        <div class="col-md-6"><b>Current Consumption Value (kWh):</b></div>
                        <div class="col-md-6"><fmt:formatNumber value="${electricityBill.currentConsumption}" pattern="0.00"/></div>
                    </div>

                    <hr>

                    <div class="row mt-4 text-end">
                        <div class="col mb-3"><b>Carbon Footprint (kg CO<sub>2</sub>): <fmt:formatNumber value="${electricityBill.carbonFootprint}" pattern="0.00"/></b></div>   
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>