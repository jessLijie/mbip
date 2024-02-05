<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin</title>
        <link rel="stylesheet" href="/static/css/Bill/History.css">
    </head>
    <%@include file="/WEB-INF/views/navbar.jsp" %>
    <body>
        
        <div class="outcontainer">
            <div class="outcontainer2">
              <table class="tablehistory">
                
                <thead>
                  <td class="col1">ID</td>
                  <td class="col2">User</td>
                  <td class="col3">Email</td>
                  <td class="col4" style="text-align:center;">Action</td>
                </thead>
                <tbody>
                  <c:forEach var="user" items="${userList}"  >
                      <tr>
                          <td>${user.id}</td>
                          <td>${user.fullname}</td>
                          <td>${user.email}</td>
                          <td><a href="/viewrecord?userid=${user.id}" style="text-decoration: none;"><i class="fas fa-eye" id="eyeIcon"></i></a></td>
                          
                      </tr>
                  </c:forEach>
              </tbody>
                
              </table>
            </div>
          </div>
    </body>
</html>
