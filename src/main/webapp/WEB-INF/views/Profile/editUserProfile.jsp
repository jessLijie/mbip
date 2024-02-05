<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editing Profile</title>
</head>
<style>
    .main-body{
        margin-top: 10px;
    }
    input{
        border: none;
        border-radius: .2rem;
        background-color: rgb(235, 235, 235);
        padding: 5px 10px;
    }
</style>

<%@include file="/WEB-INF/views/navbar.jsp" %>
<body>
    <div class="container">
        <form action="/profile/update" method="post">
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
                                    <input type="text" id="fullname" value="${fullname}" name="fullname" required placeholder="Enter your full name">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Birthday</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="date" id="birthdate" value="${birthdate}" name="birthdate" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Email</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" id="email" value="${email}" name="email" disabled>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Password</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="password" id="password" value="${password}" name="password" required>
                                    <i class="toggle-password fas fa-eye" onclick="togglePasswordVisibility('password')"></i>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Phone</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" id="phone" value="${phone}" name="phone" required placeholder="+(60)">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Address</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" id="add1" value="${add1}"  name="add1" required placeholder="Address Line 1">,
                    
                                    <input type="text" id="add2" value="${add2}" name="add2" required placeholder="Address Line 2">,
                                
                                    <input type="text" id="zipcode" value="${zipcode}"name="zipcode" required placeholder="Postal">,
                                
                                    <input type="text" id="state" value="${city}"name="city" required placeholder="City">

                                    <input type="text" id="state" value="${state}"name="state" required placeholder="State">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12">
                                    <button type="submit" style="color:white;float: right; border-radius: .5rem;border: 1px solid green; background-color: green; padding: 5px 15px;">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    </div>
    <script>
        function togglePasswordVisibility(fieldId) {
            var field = document.getElementById(fieldId);
            var eyeIcon = document.querySelector(".toggle-password");

            if (field.type === "password") {
                field.type = "text";
                eyeIcon.classList.remove("fa-eye");
                eyeIcon.classList.add("fa-eye-slash");
            } else {
                field.type = "password";
                eyeIcon.classList.remove("fa-eye-slash");
                eyeIcon.classList.add("fa-eye");
            }
        }
    </script>
</body>
</html>
