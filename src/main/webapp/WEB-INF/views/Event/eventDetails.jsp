<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Event Details</title>
    <link rel="stylesheet" href="/static/css/Event/eventDetails.css">
</head>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<body style="background-color: #ccf3ea;">

    <c:if test="${not empty event}">
        <div class="container">
            <c:choose>
                <c:when test="${user.role eq 'user'}">
                    <a href="/event/eventlist">
                        <div class="backbtn">
                        <svg  xmlns="http://www.w3.org/2000/svg" width="31" height="29" viewBox="0 0 31 29" fill="none">
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
                    </a>
                </c:when>
                <c:when test="${user.role eq 'admin'}">
                    <a href="/event/events">
                        <div class="backbtn">
                        <svg  xmlns="http://www.w3.org/2000/svg" width="31" height="29" viewBox="0 0 31 29" fill="none">
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
                    </a>
                </c:when> 
            </c:choose>

            <div class="rounded" style="background-color: #fff; border: 1px solid #ddd;">
                <div class="text-center mt-5"><h1>${event.title}</h1></div>
                <div class="image-container">
                    <img src="data:image/png;base64,${event.base64Image}" alt="Event Image" style="width: 30%">
                </div>
                <div class="event-details">
                <div class="event-content">
                    <h4 class="fw-bold">Event Details 📅</h4>
                    <br>
                    <p><strong>Date:</strong> 
                        <fmt:parseDate value="${event.startDate}" pattern="yyyy-MM-dd" var="parsedStartDate"/>
                        <fmt:formatDate value="${parsedStartDate}" pattern="yyyy-MM-dd" />
                    </p>
                    <c:if test="${!event.startDate.equals(event.endDate)}">
                        <p><strong>End Date:</strong> 
                        <fmt:parseDate value="${event.endDate}" pattern="yyyy-MM-dd" var="parsedEndDate"/>
                        <fmt:formatDate value="${parsedEndDate}" pattern="yyyy-MM-dd" /></p>
                    </c:if>
                    <p><strong>Location:</strong> ${event.location}</p>
                    <p><strong>Organizer:</strong> ${event.organizer}</p>
                   
                    <p>${event.description}</p>
                </div>
                </div>
            </div>
        </div>
    </c:if>

</body>
</html>
