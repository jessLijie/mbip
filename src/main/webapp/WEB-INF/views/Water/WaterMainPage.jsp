<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Water Main Page</title>
        <link rel="stylesheet" type="text/css" href="/css/Bill/electricity.css">

    </head>
    <jsp:include page="../navbar.jsp"/> 
    <body>
        <div class="outcontainer">
            <div class="button-container">

                <div class="historybtn" >
                    <button type="button" onclick="window.location.href='/water/WaterHistory';">
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
                    <img src="/images/water.png" alt="image" width="300" height="300" />
                </div>


                <h1><b>Water</b></h1>
                
                <div class="insertbtn" onclick="window.location.href='/water/InsertWaterConsumption'; ">
                    <button type="button">
                        
                            <div style="text-align: center;">
                                <img src="/images/image 128.png" alt="image" width="77" height="90" />
                            </div>
                            <span><b>Insert Water Bills</b> </span>
                        
                    </button>
                </div>


            </div>
        </div>
    </body>

    </html>