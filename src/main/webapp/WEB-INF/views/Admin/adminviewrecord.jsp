<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Welcome</title>
    <link rel="stylesheet" href="/static/css/Admin/viewrecord.css" />
    <script src="/static/js/Bill/adminviewrecord.js"></script>
  </head>

  <body style="background-color: #ccf3ea">
    <a href="/adminDashboard">
      <div class="backbtn">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="31"
          height="29"
          viewBox="0 0 31 29"
          fill="none"
        >
          <ellipse
            cx="15.5"
            cy="13.5"
            rx="15.5"
            ry="13.5"
            transform="matrix(-1 0 0 1 31 0)"
            fill="#A7C3F9"
          />
          <path
            d="M7.49103 14.5607C6.90524 13.9749 6.90524 13.0251 7.49103 12.4393L17.037 2.8934C17.6228 2.30761 18.5725 2.30761 19.1583 2.8934C19.7441 3.47919 19.7441 4.42893 19.1583 5.01472L10.673 13.5L19.1583 21.9853C19.7441 22.5711 19.7441 23.5208 19.1583 24.1066C18.5725 24.6924 17.6228 24.6924 17.037 24.1066L7.49103 14.5607ZM22.4482 15H8.55169V12H22.4482V15Z"
            fill="black"
          />
          <ellipse
            cx="15.5"
            cy="14.5"
            rx="15.5"
            ry="14.5"
            transform="matrix(-1 0 0 1 31 0)"
            fill="#A7C3F9"
          />
          <path
            d="M5.93934 13.9393C5.35355 14.5251 5.35355 15.4749 5.93934 16.0607L15.4853 25.6066C16.0711 26.1924 17.0208 26.1924 17.6066 25.6066C18.1924 25.0208 18.1924 24.0711 17.6066 23.4853L9.12132 15L17.6066 6.51472C18.1924 5.92893 18.1924 4.97918 17.6066 4.3934C17.0208 3.80761 16.0711 3.80761 15.4853 4.3934L5.93934 13.9393ZM26 13.5L7 13.5V16.5L26 16.5V13.5Z"
            fill="black"
          />
        </svg>
      </div>
    </a>

    <div class="outcontainer">
      <div class="outcontainer2">
        <table class="tablehistory">
          <thead>
            <td class="col1">User ID</td>
            <td class="col2">Period</td>
            <td class="col3">Current Consumption</td>
            <td class="col4">Carbon Footprint</td>
            <td class="col5">
              <div class="filter">
                <button id="showFilter">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="35"
                    height="35"
                    fill="currentColor"
                    class="bi bi-sliders"
                    viewBox="0 0 16 16"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M11.5 2a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3M9.05 3a2.5 2.5 0 0 1 4.9 0H16v1h-2.05a2.5 2.5 0 0 1-4.9 0H0V3zM4.5 7a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3M2.05 8a2.5 2.5 0 0 1 4.9 0H16v1H6.95a2.5 2.5 0 0 1-4.9 0H0V8zm9.45 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3m-2.45 1a2.5 2.5 0 0 1 4.9 0H16v1h-2.05a2.5 2.5 0 0 1-4.9 0H0v-1z"
                    />
                  </svg>
                </button>
                <div id="filterBox" class="filterbox">
                  <div class="filteryear">
                    <h2 id="yearDisplay">Bill</h2>
                  </div>

                  <hr />
                  <div class="month">
                    <div class="filtercol1">
                      <input
                        type="checkbox"
                        id="recycle"
                        name="bill"
                        value="recycle"
                      />
                      <label for="recycle">Recycle</label><br />

                      <input
                        type="checkbox"
                        id="water"
                        name="bill"
                        value="water"
                      />
                      <label for="water">Water</label><br />

                      <input
                        type="checkbox"
                        id="electricity"
                        name="bill"
                        value="electricity"
                      />
                      <label for="electricity">Electricity</label><br />
                    </div>
                  </div>
                  <div class="centerbutton">
                    <button id="applyFilter" onclick="applyFilter()">
                      Done
                    </button>
                  </div>
                </div>
              </div>
            </td>
          </thead>
          <tbody>
            <c:forEach var="bill" items="${billList}" varStatus="status">
              <tr>
                <td>${bill.userid}</td>
                <td>${bill.year}-${bill.month}</td>
                <c:choose>
                  <c:when test="${bill.billtype eq 'Recycle'}" >
                  <td>${bill.currentConsumption}kg</td>
                </c:when>
                  <c:otherwise>
                    <td>${bill.currentConsumption}kWh</td>
                  </c:otherwise>
                </c:choose>
                <td>${bill.carbonFootprint}kg CO<sub>2</sub></td>
                <td>${bill.billtype}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
