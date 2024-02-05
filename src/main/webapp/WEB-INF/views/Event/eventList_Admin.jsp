<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Events</title>
    <link rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/static/css/Bill/History.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</head>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<body style="background-color: #ccf3ea">
    <div class="text-center pt-5 pb-2">
        <h2>Event List</h2>
    </div>

    <div class="d-flex justify-content-end pt-1" style="padding-right: 130px;">
        <a href="/event/eventForm">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z"/>
        </svg>
        </a>
    </div>
    
    <div class="outcontainer">
        <div class="outcontainer2" style="margin-top: 15px">
          <table class="tablehistory">
            <thead class="text-center mb-3">
              <td class="col">ID</td>
              <td class="col3">Title</td>
              <td class="col">Start Date</td>
              <td class="col">End Date</td>
              <td class="col">Location</td>
              <td class="col">Organizer</td>
              <td class="col" style="text-align:center;">Action</td>
            </thead>
            <tbody>
              <c:forEach var="event" items="${eventList}">
                  <tr>
                      <td>${event.id}</td>
                      <td>${event.title}</td>
                      <td><fmt:formatDate value="${event.startDate}" pattern="yyyy-MM-dd" /></td>
                      <td><fmt:formatDate value="${event.endDate}" pattern="yyyy-MM-dd" /></td>
                      <td>${event.location}</td>
                      <td>${event.organizer}</td>
                      <td class="col text-center pe-0">
                        <div class="d-flex align-items-center justify-content-center">
                            <a href="<c:url value='/event/event/details/${event.id}'/>" style="text-decoration: none;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
                                    <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0"/>
                                    <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7"/>
                                </svg>
                            </a>

                            <form action="/event/deleteEvent" method="post" class="d-inline">
                                <input type="hidden" name="id" value="${event.id}">
                                <button type="submit" class="btn btn-link" onclick="return confirm('Are you sure you want to delete this event?');">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="red" class="bi bi-trash3-fill" viewBox="0 0 16 16" onmouseover="this.querySelector('path').setAttribute('fill', '#8b0000')" onmouseout="this.querySelector('path').setAttribute('fill', 'red')">
                                        <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                                    </svg>
                                </button>
                            </form>

                            <a href="<c:url value='/event/editEventForm?id=${event.id}'/>" style="text-decoration: none;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="black" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                                </svg>
                            </a>
                        </div>
                      </td>
                  </tr>
              </c:forEach>
          </tbody>
            
          </table>
        </div>
      </div>
</body>
</html>