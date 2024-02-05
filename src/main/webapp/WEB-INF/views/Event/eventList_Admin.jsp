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
                        <a href="<c:url value='/event/event/details/${event.id}'/>" style="text-decoration: none;" class="ms-1"><i class="fas fa-eye" id="eyeIcon"></i></a>

                        <form action="/event/deleteEvent" method="post" class="d-inline">
                            <input type="hidden" name="id" value="${event.id}">
                            <button type="submit" class="btn btn-link ms-3" onclick="return confirm('Are you sure you want to delete this event?');">
                                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="red" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                    <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                                </svg>
                            </button>
                        </form>
                      </td>
                  </tr>
              </c:forEach>
          </tbody>
            
          </table>
        </div>
      </div>
</body>
</html>