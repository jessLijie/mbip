<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Water History</title>
    <link rel="stylesheet" href="/css/Bill/History.css">
    <script src="js/Bill/History.js"></script>
</head>
<jsp:include page="../navbar.jsp"/> <body>
    <div class="outcontainer">
      <div class="outcontainer2">
        <table class="tablehistory">
          <thead>
            <td class="col1">No</td>
            <td class="col2">Period</td>
            <td class="col3">Address</td>
            <td class="col4">
              <div class="filter">
                <button id="showFilter"><svg
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
                    <h2>2023</h2>
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
                  </div>

                  <hr />
                  <p class="selectmonthfont">Select Month</p>
                  <div class="month">
                    <div class="filtercol1">
                      <input
                        type="checkbox"
                        id="january"
                        name="month"
                        value="January"
                      />
                      <label for="january">January</label><br />

                      <input
                        type="checkbox"
                        id="february"
                        name="month"
                        value="February"
                      />
                      <label for="february">February</label><br />

                      <input
                        type="checkbox"
                        id="march"
                        name="month"
                        value="March"
                      />
                      <label for="march">March</label><br />

                      <input
                        type="checkbox"
                        id="april"
                        name="month"
                        value="April"
                      />
                      <label for="april">April</label><br />

                      <input
                        type="checkbox"
                        id="may"
                        name="month"
                        value="May"
                      />
                      <label for="may">May</label><br />

                      <input
                        type="checkbox"
                        id="june"
                        name="month"
                        value="June"
                      />
                      <label for="june">June</label><br />
                    </div>
                    <div class="filtercol2">
                      <input
                        type="checkbox"
                        id="july"
                        name="month"
                        value="July"
                      />
                      <label for="july">July</label><br />

                      <input
                        type="checkbox"
                        id="august"
                        name="month"
                        value="August"
                      />
                      <label for="august">August</label><br />

                      <input
                        type="checkbox"
                        id="september"
                        name="month"
                        value="September"
                      />
                      <label for="september">September</label><br />

                      <input
                        type="checkbox"
                        id="october"
                        name="month"
                        value="October"
                      />
                      <label for="october">October</label><br />

                      <input
                        type="checkbox"
                        id="november"
                        name="month"
                        value="November"
                      />
                      <label for="november">November</label><br />

                      <input
                        type="checkbox"
                        id="december"
                        name="month"
                        value="December"
                      />

                      <label for="december">December</label><br /><br />
                    </div>
                  </div>
                  <div class="centerbutton">
                  <button id="applyFilter">Done</button>
                </div>
                </div>
              </div>
            </td>
          </thead>

          <tbody>
            <tr>
              <td>1</td>
              <td>12.11.2020 - 12.12.2020</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button" >
                <a href="/ElectricityHistoryView">
                <div class="viewdetailbutton">
                 
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
              </a>
              <a href="/ElectricityDownloadReport" >
                <button class="downloadbutton" >
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
            <tr>
              <td>2</td>
              <td>12.12.2020 - 12.01.2021</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button">
                <div class="viewdetailbutton">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
                <button  class="downloadbutton">
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
              </td>
            </tr>
            <tr>
              <td>3</td>
              <td>12.01.2021 - 12.02.2021</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button">
                <div class="viewdetailbutton">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
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
              </td>
            </tr>
            <tr>
              <td>4</td>
              <td>12.02.2021 - 12.03.2021</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button">
                <div class="viewdetailbutton">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
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
              </td>
            </tr>
            <tr>
              <td>5</td>
              <td>12.03.2021 - 12.04.2021</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button">
                <div class="viewdetailbutton">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
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
              </td>
            </tr>
            <tr>
              <td>6</td>
              <td>12.04.2021 - 12.05.2021</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button">
                <div class="viewdetailbutton">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
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
              </td>
            </tr>

            <tr>
              <td>7</td>
              <td>12.05.2021 - 12.06.2021</td>
              <td>No 40 Taman Bunga, 84000, Muar , Johor</td>
              <td class="button">
                <div class="viewdetailbutton">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="15"
                    height="15"
                    viewBox="0 0 15 15"
                    fill="none"
                  >
                    <path
                      d="M11.9999 14H0.999992V2.75H6.99994V1.75H0V15H12.9999V8.00001H11.9999V14Z"
                      fill="black"
                    />
                    <path
                      d="M8.99999 0V1H13.2929L5.39648 8.89648L6.10354 9.60354L13.9999 1.7071V6H14.9999V0H8.99999Z"
                      fill="black"
                    />
                  </svg>
                  View Details
                </div>
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
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>