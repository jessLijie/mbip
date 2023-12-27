<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Recycle Consumption</title>
        <link rel="stylesheet" type="text/css" href="/css/Bill/electricity.css">
<%@include file="../navbar.jsp"%> 
    </head>
    <%@include file="../navbar.jsp"%> 
    <body>
        <div class="button-container">

            <div class="historybtn">
                <button type="button" onclick="window.location.href='/RecycleHistory';">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" viewBox="0 0 25 25" fill="none">
                        <path
                            d="M24.2188 12.0865C24.2311 18.7583 18.79 24.214 12.1182 24.2187C9.23657 24.2208 6.5897 23.2163 4.50967 21.5375C3.9688 21.101 3.92837 20.2904 4.41987 19.7989L4.97002 19.2487C5.39038 18.8284 6.06147 18.7824 6.5272 19.1519C8.05967 20.3679 9.99907 21.0938 12.1094 21.0938C17.0754 21.0938 21.0938 17.0747 21.0938 12.1094C21.0938 7.14331 17.0747 3.125 12.1094 3.125C9.72588 3.125 7.56108 4.05122 5.95371 5.56309L8.43193 8.04131C8.92412 8.5335 8.57554 9.375 7.87954 9.375H0.78125C0.349756 9.375 0 9.02524 0 8.59375V1.49546C0 0.799463 0.841504 0.450879 1.33369 0.943018L3.74443 3.35376C5.91841 1.27617 8.86484 0 12.1094 0C18.7896 0 24.2064 5.40918 24.2188 12.0865ZM15.3852 15.9333L15.8648 15.3167C16.2622 14.8058 16.1701 14.0695 15.6592 13.6722L13.6719 12.1264V7.03125C13.6719 6.38403 13.1472 5.85938 12.5 5.85938H11.7188C11.0715 5.85938 10.5469 6.38403 10.5469 7.03125V13.6548L13.7407 16.1389C14.2516 16.5362 14.9878 16.4442 15.3852 15.9333Z"
                            fill="black" />
                    </svg>
                    <span>History</span>
                </button>
            </div>
        </div>

        <div class="container">

            <div style="text-align: center;">
                <img src="/images/Recycle0.png" alt="image" width="300" height="300" />
            </div>
            

            <h1><b>Recycle</b></h1>
            <a href="/InsertRecycleConsumption">
            <div class="insertbtn">
                
                <button type="button">
                
                    <div style="text-align: center;">
                        <img src="/images/image 128.png" alt="image" width="77" height="90" />
                    </div>
                    <!-- <svg xmlns="http://www.w3.org/2000/svg" width="77" height="90" viewBox="0 0 77 90" fill="none">
                        <path
                            d="M53.8994 67.5H19.2494V27H42.3494V22.5H19.2494C17.1319 22.5 15.3994 24.525 15.3994 27V67.5C15.3994 69.975 17.1319 72 19.2494 72H53.8994C56.0169 72 57.7494 69.975 57.7494 67.5V40.5H53.8994V67.5Z"
                            fill="black" />
                        <path d="M26.9482 36V40.5H42.3482H46.1982V36H26.9482Z" fill="black" />
                        <path d="M26.9482 45H46.1982V49.5H26.9482V45Z" fill="black" />
                        <path d="M26.9482 54H46.1982V58.5H26.9482V54Z" fill="black" />
                        <path d="M57.7482 13.5H53.8982V22.5H46.1982V27H53.8982V36H57.7482V27H65.4482V22.5H57.7482V13.5Z"
                            fill="black" />
                    </svg> -->
                    <span><b>Insert Recycle Record</b> </span>
                </button>
            </a>
            </div>


        </div>
    </body>

    </html>