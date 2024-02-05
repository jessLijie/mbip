<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Welcome</title>
    <link rel="stylesheet" href="/static/css/Bill/History.css" />
    <script src="/static/js/Bill/History.js"></script>
  </head>
  <%@include file= "/WEB-INF/views/navbar.jsp" %>
  <body style="background-color: #ccf3ea">
    <a href="/recycle/InsertRecycleConsumption">
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
            <td class="col1">No</td>
            <td class="col2">Period</td>
            <td class="col3">Address</td>
            <td class="col4">
              <a href="/recycle/Recycle"><svg
                xmlns="http://www.w3.org/2000/svg"
                width="35"
                height="33"
                fill="currentColor"
                class="bi bi-plus-circle-fill"
                viewBox="0 0 16 16"
              >
                <path
                  d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z"
                />
              </svg>
            </a>
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
                    <button id="leftArrowYear">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="12"
                        height="14"
                        viewBox="0 0 12 14"
                        fill="none"
                      >
                        <path
                          d="M9.72973 0L12 1.63333L4.54054 7L12 12.3667L9.72973 14L0 7L9.72973 0Z"
                          fill="#243E69"
                        />
                      </svg>
                    </button>
                    <h2 id="yearDisplay">2023</h2>
                    <button id="rightArrowYear">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="11"
                        height="14"
                        viewBox="0 0 11 14"
                        fill="none"
                      >
                        <path
                          d="M2.08108 14L0 12.3667L6.83784 7L0 1.63333L2.08108 0L11 7L2.08108 14Z"
                          fill="#243E69"
                        />
                      </svg>
                    </button>
                  </div>

                  <hr />
                  <p class="selectmonthfont">Select Month</p>
                  <div class="month">
                    <div class="filtercol1">
                      <input
                        type="checkbox"
                        id="january"
                        name="month"
                        value="01"
                      />
                      <label for="january">January</label><br />

                      <input
                        type="checkbox"
                        id="february"
                        name="month"
                        value="02"
                      />
                      <label for="february">February</label><br />

                      <input
                        type="checkbox"
                        id="march"
                        name="month"
                        value="03"
                      />
                      <label for="march">March</label><br />

                      <input
                        type="checkbox"
                        id="april"
                        name="month"
                        value="04"
                      />
                      <label for="april">April</label><br />

                      <input type="checkbox" id="may" name="month" value="05" />
                      <label for="may">May</label><br />

                      <input
                        type="checkbox"
                        id="june"
                        name="month"
                        value="06"
                      />
                      <label for="june">June</label><br />
                    </div>
                    <div class="filtercol2">
                      <input
                        type="checkbox"
                        id="july"
                        name="month"
                        value="07"
                      />
                      <label for="july">July</label><br />

                      <input
                        type="checkbox"
                        id="august"
                        name="month"
                        value="08"
                      />
                      <label for="august">August</label><br />

                      <input
                        type="checkbox"
                        id="september"
                        name="month"
                        value="09"
                      />
                      <label for="september">September</label><br />

                      <input
                        type="checkbox"
                        id="october"
                        name="month"
                        value="10"
                      />
                      <label for="october">October</label><br />

                      <input
                        type="checkbox"
                        id="november"
                        name="month"
                        value="11"
                      />
                      <label for="november">November</label><br />

                      <input
                        type="checkbox"
                        id="december"
                        name="month"
                        value="12"
                      />

                      <label for="december">December</label><br /><br />
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
            <c:forEach var="recycle" items="${recycleList}" varStatus="status">
              <tr>
                <td>${status.index + 1}</td>
                <td>${recycle.year}-${recycle.month}</td>
                <td>${recycle.address}</td>

                <td class="button">
                  <div class="viewdetailbutton">
                    <a
                      href="/recycle/RecycleHistoryDetail?billId=${recycle.id}"
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-eye-fill"
                        viewBox="0 0 16 16"
                      >
                        <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0" />
                        <path
                          d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7"
                        />
                      </svg>
                    </a>
                    <a
                    href="/recycle/RecycleUpdateBill?billId=${recycle.id}"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-pencil-square"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
                      />
                      <path
                        fill-rule="evenodd"
                        d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"
                      />
                    </svg>
                    </a>
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      class="bi bi-trash-fill"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"
                      />
                    </svg>
                  </div>

                  <a href="/recycle/RecycleDownloadReport?billId=${recycle.id}">
                    <button class="downloadbutton">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="13"
                        height="14"
                        viewBox="0 0 13 14"
                        fill="none"
                      >
                        <path
                          d="M0 14H13V12.3529H0V14ZM13 4.94118H9.28571V0H3.71429V4.94118H0L6.5 10.7059L13 4.94118Z"
                          fill="black"
                        />
                      </svg>
                      Download
                    </button>
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
