<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Jersey Web Application for Api Testing</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body, h1, h2, h3, h4, h5, h6 {
        font-family: "Raleway", sans-serif
    }
    body, html {
        height: 100%;
        line-height: 1.8;
    }
    /* Full height image header */
    .bgimg-1 {
        background-position: center;
        background-size: cover;
        background-image: url("https://image.ibb.co/bPrM8b/bulb.jpg");
        min-height: 100%;
    }
</style>
<body>
<!-- Header with full-height image -->
<header class="bgimg-1 w3-display-container w3-grayscale-min" id="home">
    <div class="w3-display-left w3-text-white" style="padding:48px">
        <span class="w3-jumbo w3-hide-small">Start something that matters</span><br>
        <span class="w3-xxlarge w3-hide-large w3-hide-medium">Start something that matters</span><br>
        <div class="w3-center">
            <span class="w3-large">Stop wasting valuable time with projects that just isn't for you.</span>
            <p><a href="https://github.com/makhter01?tab=repositories"
                  class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">
                My GitHub</a></p>
            <h3>Team 2 - Service API</h3>
            <a href="/team2-service-webapp/api/AllEmployeeResources">All Employee Resources</a>
            <br>
            <p id="employee">Single Employee Resources</p>
            <button onclick="myFunction()">Employee ID</button>
            <!-- Create custom url -->
            <script>
                function myFunction() {
                    var id = prompt("Please enter an Employee ID", "590a4acd35522970c7956cdf");
                    if (id != null) {
                        document.getElementById("employee").innerHTML =
                            "<a href=\"/team2-service-webapp/api/SingleEmployeeResources/" + id + "\">Single Employee Resources</a>";
                    }
                }
            </script>
        </div>
    </div>
</header>
</body>
</html>
