<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Event List</title>
    <link rel="stylesheet" href="/static/css/Event/event.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-xwMca4ia/Z5Mt0AMXl5U9VH3z2P2a5R/MV2p4S/QzvvXtu/oMIga8dSFbYXwwlK3Z1wF8e5Zjj6IdQHmk65J2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="/static/js/Event.js"></script>
</head>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<body style="background-color: #ccf3ea;">
    <div class="title">Upcoming Event</div>
    <div class="filter-form">
        <form action="/event/FilterEventName" method="get">
            <input type="text" id="eventName" name="eventName" placeholder="Event Name">
            <button type="submit">Filter</button>
        </form>
    
    </div>

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
                style="display: inline-block; margin-right: 5px;"/>

              <label for="december" style="display: inline-block; margin-right: 5px;">December</label><br /><br />
            </div>
          </div>
          <div class="centerbutton">
            <button id="applyFilter" onclick="applyFilterEvent()">Submit</button>
          </div>
    
        </div>
    </div>
    
    <div class="outcontainer">
        <c:forEach var="event" items="${eventList}" varStatus="status">
                <div class="event-container">
                    <hr>
                    <div class="image-container">
                        <img src="/static/images/event.png" alt="event Logo" width="90" height="90">
                    </div>
                    <div class="info-container">
                        <h3>" ${event.title}</h3>
                        <p>📅

                            <strong>Date:</strong> 
                            <fmt:parseDate value="${event.startDate}" pattern="yyyy-MM-dd" var="parsedStartDate"/>
                            <fmt:formatDate value="${parsedStartDate}" pattern="yyyy-MM-dd" />
                        </p>
                        <c:if test="${!event.startDate.equals(event.endDate)}">
                            <p>📅 <strong>End Date:</strong> 
                            <fmt:parseDate value="${event.endDate}" pattern="yyyy-MM-dd" var="parsedEndDate"/>
                            <fmt:formatDate value="${parsedEndDate}" pattern="yyyy-MM-dd" /></p>
                        </c:if>
                        <p>📍 <strong>Location:</strong> ${event.location}</p>
                        <p>👥 <strong>Organizer:</strong> ${event.organizer}</p>
                       
                    </div> <a class="view-details" href="<c:url value='/event/event/details/${event.id}'/>">View Details</a>
                    <hr>
                </div>
        </c:forEach>
    </div>
    <div class="pagination justify-content-center">
        <ul class="pagination">
            <c:choose>
                <c:when test="${currentPage > 1 && not empty year && not empty month}">
                    <!-- Pagination links for /event/applyFilterEvent when filtered -->
                    <c:url var="prevPageUrl" value="/event/applyFilterEvent">
                        <c:param name="page" value="${currentPage - 1}" />
                        <c:param name="year" value="${year}" />
                        <c:param name="month" value="${month}" />
                    </c:url>
                    <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                        <a class="page-link" href="${prevPageUrl}" tabindex="-1" aria-disabled="true">&laquo; Previous</a>
                    </li>
    
                    <c:forEach var="pageNumber" begin="1" end="${totalPages}" step="1">
                        <c:url var="pageUrl" value="/event/applyFilterEvent">
                            <c:param name="page" value="${pageNumber}" />
                            <c:param name="year" value="${year}" />
                            <c:param name="month" value="${month}" />
                        </c:url>
                        <li class="page-item ${pageNumber eq currentPage ? 'active' : ''}">
                            <a class="page-link" href="${pageUrl}">${pageNumber}</a>
                        </li>
                    </c:forEach>
    
                    <c:url var="nextPageUrl" value="/event/applyFilterEvent">
                        <c:param name="page" value="${currentPage + 1}" />
                        <c:param name="year" value="${year}" />
                        <c:param name="month" value="${month}" />
                    </c:url>
                    <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="${nextPageUrl}">Next &raquo;</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <!-- Pagination links for /event/eventlist when not filtered -->
                    <c:url var="prevPageUrl" value="/event/eventlist">
                        <c:param name="page" value="${currentPage - 1}" />
                    </c:url>
                    <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                        <a class="page-link" href="${prevPageUrl}" tabindex="-1" aria-disabled="true">&laquo; Previous</a>
                    </li>
    
                    <c:forEach var="pageNumber" begin="1" end="${totalPages}" step="1">
                        <c:url var="pageUrl" value="/event/eventlist">
                            <c:param name="page" value="${pageNumber}" />
                        </c:url>
                        <li class="page-item ${pageNumber eq currentPage ? 'active' : ''}">
                            <a class="page-link" href="${pageUrl}">${pageNumber}</a>
                        </li>
                    </c:forEach>
    
                    <c:url var="nextPageUrl" value="/event/eventlist">
                        <c:param name="page" value="${currentPage + 1}" />
                    </c:url>
                    <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="${nextPageUrl}">Next &raquo;</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    
    
</body>
</html>
