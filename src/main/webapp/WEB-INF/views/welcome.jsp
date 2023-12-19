<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<jsp:include page="navbar.jsp"/> 
<body>
    Hello ${name}. Welome to the system!
    <input type="button" name="click" value="Click on me!" onclick="showBox()"/>
    <script src="/js/script.js"></script>
</body>
</html>