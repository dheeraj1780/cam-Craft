<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="camResult.camResult.CamProfileSegment" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/main.js"></script>
<meta charset="ISO-8859-1">
<title>Cam Details</title>
</head>
<body>
    <div class="header">
        <h1 id="title">CAM Details</h1>
    </div>
    <div class="content">
        <h2>Input</h2>
        <form action="result" method="POST">
            <!-- Follower Specifications -->
            <fieldset>
                <legend>Follower Specifications</legend>
                <label for="followerType">Type of Follower:</label>
                <select id="followerType" name="followerType" required onchange="toggleFollowerRadius()">
                    <option value="knife-edge">Knife-edge</option>
                    <option value="roller">Roller</option>
                    <option value="flat-faced">Flat-faced</option>
                    <option value="spherical-faced">Spherical-faced</option>
                </select><br><br>
                <label for="followerRadius">Follower Radius:</label>
                <input type="number" id="followerRadius" name="followerRadius" step="0.01" disabled><br><br>
            </fieldset>

            <!-- Cam Profile Specifications -->
            <fieldset>
                <legend>Cam Profile Specifications</legend>
                <label for="baseCircleRadius">Base Circle Radius:</label>
                <input type="number" id="baseCircleRadius" name="baseCircleRadius" step="0.01" required><br><br>
                <label for="lift">Lift (Rise):</label>
                <input type="number" id="lift" name="lift" step="0.01" required><br><br>
            </fieldset>

            <!-- Motion Profile -->
            <fieldset>
                <legend>Motion Profile</legend>
                <label for="numSegments">Number of Segments:</label>
                <input type="number" id="numSegments" name="numSegments" required onchange="generateSegments()"><br><br>
                <div id="segments">
                    <!-- Dynamic content will be added here -->
                </div>
            </fieldset>

            <!-- Output Preferences -->
            <fieldset>
                <legend>Output Preferences</legend>
                <label for="resolution">Resolution (degrees):</label>
                <input type="number" id="resolution" name="resolution" step="1" required><br><br>
            </fieldset>

            <button type="submit">Generate Cam Diagram</button>
        </form>
    </div>
    <div class="output">
        <h2>Cam Profile Results</h2>
        <table border="1">
            <tr>
                <th>Cam Angle (°)</th>
                <th>Displacement (mm)</th>
                <th>Velocity (mm/°)</th>
                <th>Acceleration (mm/°²)</th>
            </tr>
            <%
                List<CamProfileSegment> camProfile = (List<CamProfileSegment>) request.getAttribute("camProfile");
                if (camProfile != null) {
                    for (CamProfileSegment segment : camProfile) {
                        out.println("<tr>");
                        out.println("<td>" + segment.getAngle() + "</td>");
                        out.println("<td>" + segment.getDisplacement() + "</td>");
                        out.println("<td>" + segment.getVelocity() + "</td>");
                        out.println("<td>" + segment.getAcceleration() + "</td>");
                        out.println("</tr>");
                    }
                }
            %>
        </table>
    </div>
    <script src="../../lib/bootstrap/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
