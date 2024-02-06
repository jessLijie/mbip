<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
</head>
<style>
    .main-body{
        margin-top: 10px;
    }
    input{
      border: none;
      background-color: white;
    }

    
</style>

<%@include file= "/WEB-INF/views/navbar.jsp" %>
<body>
    <div class="container">
        <div class="main-body">
        
        
              <div class="row gutters-sm">
                <div class="col-md-4 mb-3">
                  <div class="card">
                    <div class="card-body">
                      <div class="d-flex flex-column align-items-center text-center">
                        <img src="https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg" alt="Admin" class="rounded-circle" width="150">
                        <div class="mt-3">
                          <h4>${fullname}</h4>
                          <p class="text-secondary mb-1">Student ID: ${matricsNo}</p>
                          <p class="text-muted font-size-sm">Username: ${name}</p>
                          
                        </div>
                      </div>
                    </div>
                  </div>
                 
                </div>
                <div class="col-md-8">
                  <div class="card mb-3">
                    <div class="card-body">
                      <div class="row">
                        <div class="col-sm-3">
                          <h6 class="mb-0">Full Name</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                          ${fullname}
                        </div>
                      </div>
                      <hr>
                      <div class="row">
                        <div class="col-sm-3">
                          <h6 class="mb-0">Birthday</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                          ${birthdate}
                        </div>
                      </div>
                      <hr>
                      <div class="row">
                        <div class="col-sm-3">
                          <h6 class="mb-0">Email</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                          ${email}
                        </div>
                      </div>
                      <hr>
                      <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Password</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <input type="password" id="password" value="${password}" disabled>
                            <i class="toggle-password fas fa-eye" onclick="togglePasswordVisibility()"></i>

                        </div>
                    </div><hr>
                      <div class="row">
                        <div class="col-sm-3">
                          <h6 class="mb-0">Phone</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                          ${phone}
                        </div>
                    </div>
                      <hr>
                      <div class="row">
                        <div class="col-sm-3">
                          <h6 class="mb-0">Address</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                          ${add1}, ${add2}, ${zipcode},${city}, ${state}
                        </div>
                      </div>
                      <hr>
                      <div class="row">
                        <div class="col-sm-12">
                          <a class="btn btn-info " href="/profile/edit" style="float: right;">Edit Info</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
    
            </div>
        </div>
        <script>
          function togglePasswordVisibility() {
              var passwordInput = document.getElementById("password");
              var eyeIcon = document.querySelector(".toggle-password");
      
              if (passwordInput.type === "password") {
                  passwordInput.type = "text";
                  eyeIcon.classList.remove("fa-eye");
                  eyeIcon.classList.add("fa-eye-slash");
              } else {
                  passwordInput.type = "password";
                  eyeIcon.classList.remove("fa-eye-slash");
                  eyeIcon.classList.add("fa-eye");
              }
          }
      </script>
</body>
</html>