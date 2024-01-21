<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" 
pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Welcome</title>
    <link rel="stylesheet" href="../static/css/login.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
  </head>

  <body>

    <div class="container" id="container"  style="margin-top: 50px;">
      <div class="form-container sign-up-container">
        <!-- Sign Up -->
        <form action="/profile/insert">
          <h1>Create Account</h1>
          <div class="social-container">
            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
          </div>
          <span>or use your email for registration</span>
          <input type="text" placeholder="Username" name="username" required/>
          <input type="email" placeholder="Email" name="email"required />
          <input type="text" placeholder="Matrics No." name="matricsNo" required />
          <input type="password" placeholder="Password" name="password" id="passwordInput" required />
          <button type="button" id="togglePassword">
            <i class="fas fa-eye" id="eyeIcon"></i> Show Password
            </button>
          <br>
          <button>Sign Up</button>
        </form>
      </div>
      <div class="form-container sign-in-container">
        <!-- LoginController -->
        <form action="login">
          <h1>Sign in</h1>
          <div class="social-container">
            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
          </div>
          <span>or use your account</span>
          <input type="text" placeholder="Username" name="username"required />
          <input type="password" placeholder="Password" name="password" id="passwordInput" required />
          <button type="button" id="togglePassword">
            <i class="fas fa-eye" id="eyeIcon"></i> Show Password
            </button>
          <a href="#">Forgot your password?</a>
          <!-- temp -->
          <button type="submit">Sign In</button>
          <!-- <a href="/userDashboard"style="text-decoration: none;"></a> -->
        </form>
      </div>
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <h1>Welcome Back!</h1>
            <p>
              To keep connected with us please login with your personal info
            </p>
            <button class="ghost" id="signIn">Sign In</button>
          </div>
          <div class="overlay-panel overlay-right">
            <h1>Hello, Friend!</h1>
            <p>Enter your personal details and start journey with us</p>
            <button class="ghost" id="signUp">Sign Up</button>
          </div>
        </div>
      </div>
    </div>
<script>
    document.getElementById("togglePassword").addEventListener("click", function () {
        var passwordInput = document.getElementById("passwordInput");
        var eyeIcon = document.getElementById("eyeIcon");

        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            eyeIcon.classList.remove("fa-eye");
            eyeIcon.classList.add("fa-eye-slash");
        } else {
            passwordInput.type = "password";
            eyeIcon.classList.remove("fa-eye-slash");
            eyeIcon.classList.add("fa-eye");
        }
    });
</script>

    <script src="../static/js/script.js"></script>
    <script src="../static/js/login.js"></script>
  </body>
</html>