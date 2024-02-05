<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert Water Consumption</title>
    <link rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</head>
<script>
    function validateFileSize() {
        var fileInput = document.getElementById('bill_img');  // Correct id here
        var maxFileSize = 50 * 1024; // 50KB in bytes
    
        if (fileInput.files.length > 0) {
            var fileSize = fileInput.files[0].size; // in bytes
    
            if (fileSize > maxFileSize) {
                alert('File size exceeds 50KB. Please choose a smaller file.');
                // Reset the file input
                fileInput.value = '';
            }
        }
    }
</script>
<%@include file="../navbar.jsp"%> 
<body style="background-color: #CCF3EA">
    <button type="button" class="px-4 p-2 m-4 mb-0 ms-5 rounded shadow" style="background-color: #A7C3F9; border:none;" onclick="window.location.href='/water/WaterHistory;'">
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" viewBox="0 0 25 25" fill="none">
            <path
                d="M24.2188 12.0865C24.2311 18.7583 18.79 24.214 12.1182 24.2187C9.23657 24.2208 6.5897 23.2163 4.50967 21.5375C3.9688 21.101 3.92837 20.2904 4.41987 19.7989L4.97002 19.2487C5.39038 18.8284 6.06147 18.7824 6.5272 19.1519C8.05967 20.3679 9.99907 21.0938 12.1094 21.0938C17.0754 21.0938 21.0938 17.0747 21.0938 12.1094C21.0938 7.14331 17.0747 3.125 12.1094 3.125C9.72588 3.125 7.56108 4.05122 5.95371 5.56309L8.43193 8.04131C8.92412 8.5335 8.57554 9.375 7.87954 9.375H0.78125C0.349756 9.375 0 9.02524 0 8.59375V1.49546C0 0.799463 0.841504 0.450879 1.33369 0.943018L3.74443 3.35376C5.91841 1.27617 8.86484 0 12.1094 0C18.7896 0 24.2064 5.40918 24.2188 12.0865ZM15.3852 15.9333L15.8648 15.3167C16.2622 14.8058 16.1701 14.0695 15.6592 13.6722L13.6719 12.1264V7.03125C13.6719 6.38403 13.1472 5.85938 12.5 5.85938H11.7188C11.0715 5.85938 10.5469 6.38403 10.5469 7.03125V13.6548L13.7407 16.1389C14.2516 16.5362 14.9878 16.4442 15.3852 15.9333Z"
                fill="black" />
        </svg>
        <span class="ms-2">History</span>
    </button>
    
    <div class="container mt-2 justify-content-center" style="width: 60%">
        <form action="/water/InsertWaterConsumption" method="post">
            <div class="bg-white p-4 px-5 rounded shadow m-3"> 
                <!--address section-->
                <div class="row mb-3">
                    <div class="col-md-2">
                        <label for="address" class="form-label"><b>Address</b></label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" id="address1" name="address1" value="${user.add1}" class="form-control mb-3" required><!--retrive address1-->
                        <input type="text" id="address2" name="address2" value="${user.add2}" class="form-control" required><!--retrive address2-->
                    </div>
                </div>

                <div class="row mb-3 align-items-center">
                    <div class="col-md-2">
                        <label for="state"><b>State</b></label>
                    </div>
                    <div class="col-md-4">
                        <input type="text" id="state" name="state" value="${user.state}" class="form-control" required><!--retrieve state-->
                    </div>
                    <div class="col-md-2">
                        <label for="city"><b>City</b></label>
                    </div>
                    <div class="col-md-4">
                        <input type="text" id="city" name="city" value="${user.city}" class="form-control" required><!--retrieve city-->
                    </div>
                </div>

                <div class="row mb-4 align-items-center">
                    <div class="col-md-2">
                        <label for="postcode"><b>Postcode</b></label>
                    </div>
                    <div class="col-md-4">
                        <input type="text" id="postcode" name="postcode" maxlength="5" value="${user.zipcode}" class="form-control" required><!--retrieve postcode-->
                    </div>
                </div>
            
                <hr>
                
                <!--insert section-->
                <b class="mt-4 text-center d-block fs-5">WATER CONSUMPTION</b>

                <div class="row mb-3 mt-4 align-items-center">
                    <div class="col-md-6">
                        <label for="period"><b>Period</b></label>
                    </div>
                    <div class="col-md-6">
                        <input type="month" id="period" name="period" class="form-control" required>
                    </div>
                </div>
                <div class="row mb-5 align-items-center">
                    <div class="col-md-6">
                        <label for="totalWConsumption"><b>Total Consumption (m<sup>3</sup>)</b></label>
                    </div>
                    <div class="col-md-6">
                        <input type="number" id="totalWConsumption" name="totalWConsumption" class="form-control" required>
                    </div>
                </div>

                <!-- Upload Section -->
                <p class="mt-4" style="color:red"><b>**Please upload your bills for reference (eg: jpg, png)</b></p>

                <div class="row mb-3">
                    <div class="col-md-6 mb-3">
                        <div class="input-group">
                            <input type="file" id="bill_img" name="bill_img" class="form-control" accept=".jpg, .jpeg, .png" required onchange="validateFileSize()">
                        </div>
                    </div>
                </div>

                <div class="text-center mt-4">
                    <a href="/water/InsertWaterConsumption"><button type="submit" class="btn btn-lg px-5" style="background-color: #4C8BC6; color: white;"><b>SUBMIT</b></button></a>
                </div>
            </div>
        </form>
    </div>    
</body>