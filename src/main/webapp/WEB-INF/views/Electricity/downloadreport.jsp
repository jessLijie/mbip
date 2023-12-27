<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
        <script type="text/javascript" src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/Bill/download.css">
        <title>Electricity Bills</title>
        <script src="js/Bill/download.js"></script>
    </head>
    <%@include file="../navbar.jsp"%> 
    <body>
        <div class="page" size="A4">
            <div class="center-container">
                <img src="/images/mbip.png" alt="MBIP Logo" width="650" height="120">
            </div>
            <h3>Electricity Bills</h3>
            <table>
                <tr>
                    <td><b>Name: </b></td>
                    <td>Hermione Granger</td>
                </tr>
                <tr>
                    <td><b>Email: </b></td>
                    <td>herm@gmail.com</td>
                </tr>
                <tr>
                    <td><b>Phone Number: </b></td>
                    <td>+(60)12-345 6789</td>
                </tr>
                <tr>
                    <td><b>Address: </b></td>
                    <td>Lot 1 Imperia, Jalan Laksamana 1, <br> Puteri Harbour,
                        <br> 79000 Iskandar Puteri.
                    </td>
                </tr>
                <tr>
                    <td><b>Date:</b></td>
                    <td>17.01.2021 - 16.02.2021 (31 days)</td>
                </tr>
                <tr>
                    <td><b>Prorata Factor:</b></td>
                    <td>1.00000</td>
                </tr>
                <tr>
                    <td><b>Current Consumption Value (kWh):</b></td>
                    <td>186</td>
                </tr>
                <tr>
                    <td><b>Carbon Footprint (kg CO<sub>2</sub>): </b></td>
                    <td>108.624 kgCO<sub>2</sub></td>
                </tr>

            </table>
            <div id="downloadButtonContainer">
                <button onclick="printReport()">Download PDF</button>
            </div>
        </div>
    </body>

    </html>