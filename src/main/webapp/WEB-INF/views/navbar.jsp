<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">

</head>
<style>
    .navbar-brand{
        padding-left: 50px;
    }
    .profile-sec{
        float: right;
        padding-right:50px;
    }
    .dropdown:hover>.dropdown-menu {
  display: block;
  margin-right: 10px;
  padding-right: 10px;
}

.dropdown>.dropdown-toggle:active {
  /*Without this, clicking will make it sticky*/
    pointer-events: none;
}
</style>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">MBIP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/userDashboard">Dashboard</a>
            </li>
            
            <li class="nav-item">
              <a class="nav-link" href="/Water">Water</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/Electricity">Electricity</a>
              </li>

              <li class="nav-item">
                <a class="nav-link" href="/Recycle">Recycle</a>
              </li>
            
          </ul>
        
        </div>

        <div class="profile-sec">
            <div class="dropdown">
              <button
                class="btn btn-primary dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-mdb-toggle="dropdown"
                aria-expanded="false"
              ><i class="bi bi-person"></i>
              Profile
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <li><a class="dropdown-item" href="profile">View Profile</a></li>
                <li><a class="dropdown-item" href="login">Logout</a></li>
              </ul>
            </div>
        </div>
      </nav>
      
      

</body>
</html>