<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Add Event Form</title>
    <link rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</head>
<script>
function validateFileSize() {
    var fileInput = document.getElementById('event_img');  // Correct id here
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
<%@include file="/WEB-INF/views/navbar.jsp" %>
<body style="background-color: #CCF3EA">
    <div class="col-auto mx-5 mb-4 mt-5" onclick="window.location.href='/event/events';">
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
    
    <div class="container mt-2 justify-content-center" style="width: 60%">
        <form action="/event/editEvent" method="post" enctype="multipart/form-data">
            <div class="bg-white p-4 px-5 rounded shadow m-3"> 
                <b class="mb-4 mt-2 text-center d-block fs-3">EVENT FORM</b>
                <input type="hidden" id="eventId" name="eventId" value="${event.id}">
                <!--general event info-->
                <div class="row mb-3 align-items-center">
                    <div class="col-md-2">
                        <label for="title" class="form-label"><b>Title</b></label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" id="title" name="title" value="${event.title}" class="form-control" required>
                    </div>
                </div>

                <div class="row mb-3 align-items-center">
                    <div class="col-md-2">
                        <label for="sdate"><b>Start Date</b></label>
                    </div>
                    <div class="col-md-4">
                        <input type="date" id="sdate" name="sdate" value="<fmt:formatDate value="${event.startDate}" pattern="yyyy-MM-dd" />" class="form-control" required>
                    </div>
                </div>

                <div class="row mb-3 align-items-center">
                    <div class="col-md-2">
                        <label for="edate"><b>End Date</b></label>
                    </div>
                    <div class="col-md-4">
                        <input type="date" id="edate" name="edate" value="<fmt:formatDate value="${event.endDate}" pattern='yyyy-MM-dd' />" class="form-control mb-3" required>
                    </div>
                </div>
            
                <hr>
                
                <!--details event info-->
                <div class="row mb-3 mt-4 align-items-center">
                    <div class="col-md-2">
                        <label for="location"><b>Location</b></label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" id="location" name="location" value="${event.location}" class="form-control" required>
                    </div>
                </div>

                <div class="row mb-3 align-items-center">
                    <div class="col-md-2">
                        <label for="organizer"><b>Organizer</b></label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" id="organizer" name="organizer" value="${event.organizer}" class="form-control" required>
                    </div>
                </div>

                <div class="row mb-5 align-items-center">
                    <div class="col-md-2">
                        <label for="desc"><b>Description</b></label>
                    </div>
                    <div class="col-md-10">
                        <textarea id="desc" name="desc" class="form-control" required>${event.description}</textarea>
                    </div>
                </div>

                <!-- Display the current image -->
                <div class="d-flex align-items-center justify-content-center">
                    <img src="data:image/jpeg;base64,${eventImg}" alt="Event Image" style="width: 40%">
                </div>

                <!-- Upload Section -->
                <p style="color:red"><b>**Please upload a new image for reference (eg: jpg, png)</b></p>

                <div class="row mb-3">
                    <div class="col-md-6 mb-3">
                        <div class="input-group">
                            <input type="file" id="event_img" name="event_img" class="form-control" accept=".jpg, .jpeg, .png" required onchange="validateFileSize()">
                        </div>
                    </div>
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-lg px-5" style="background-color: #4C8BC6; color: white;"><b>UPDATE</b></button>
                </div>
            </div>
        </form>
    </div>    
</body>