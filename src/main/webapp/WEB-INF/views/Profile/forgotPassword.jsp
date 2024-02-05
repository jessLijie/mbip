<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Welcome</title>
    <link rel="stylesheet" href="../static/css/login.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
    />
  </head>

  <body>
      <center>
        <div style="background-color: none;">

      
          <!-- LoginController -->
          <form action="/updatePassword">
            <h1>Reset Password</h1>

            <input
              type="text"
              placeholder="Username"
              name="username"
              required
            />
            <input
              type="text"
              placeholder="Fullname"
              name="fullname"
              required
            />
            <input type="text" placeholder="Email" name="email" required />
            <input
              type="password"
              placeholder="Password"
              name="password"
              id="passwordInput"
              required
            />
            <input
              type="password"
              placeholder="Enter password again"
              name="password"
              id="passwordInput2"
              required
            />

            <button type="button" id="togglePassword">
              <i class="fas fa-eye" id="eyeIcon"></i> Show Password
            </button>
            <br />
            <button type="submit" onclick="updatePassword()">
              Update Passsword
            </button>
            <!-- <a href="/userDashboard"style="text-decoration: none;"></a> -->
          </form>
        </div>
      </center>
    <script>
      function checkPasswords() {
        var password1 = document.getElementById("passwordInput").value;
        var password2 = document.getElementById("passwordInput2").value;

        if (password1 !== password2) {
          alert(
            "Passwords do not match. Please enter the same password in both fields."
          );
          return false; 
        }
        return true;
      }
      function updatePassword() {
        window.alert("Password updated successfully!");
      }
      document
        .getElementById("togglePassword")
        .addEventListener("click", function () {
          var passwordInput = document.getElementById("passwordInput");
          var passwordInput2 = document.getElementById("passwordInput2");
          var eyeIcon = document.getElementById("eyeIcon");

          if (passwordInput.type === "password") {
            passwordInput.type = "text";
            passwordInput2.type = "text";
            eyeIcon.classList.remove("fa-eye");
            eyeIcon.classList.add("fa-eye-slash");
          } else {
            passwordInput.type = "password";
            passwordInput2.type = "password";
            eyeIcon.classList.remove("fa-eye-slash");
            eyeIcon.classList.add("fa-eye");
          }
        });
    </script>

    <script src="../static/js/script.js"></script>
    <script src="../static/js/login.js"></script>
  </body>
</html>
