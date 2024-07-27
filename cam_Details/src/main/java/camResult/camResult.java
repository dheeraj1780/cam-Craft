package camResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/result")
public class camResult extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public camResult() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve follower specifications
        String followerType = request.getParameter("followerType");
        String followerRadiusStr = request.getParameter("followerRadius");
        double followerRadius = followerRadiusStr != null && !followerRadiusStr.isEmpty() ? Double.parseDouble(followerRadiusStr) : 0.0;

        // Retrieve cam profile specifications
        double baseCircleRadius = Double.parseDouble(request.getParameter("baseCircleRadius"));
        double lift = Double.parseDouble(request.getParameter("lift"));

        // Retrieve motion profile
        int numSegments = Integer.parseInt(request.getParameter("numSegments"));
        Segment[] segments = new Segment[numSegments];

        for (int i = 0; i < numSegments; i++) {
            String segmentType = request.getParameter("segmentType" + i);
            double segmentAngle = Double.parseDouble(request.getParameter("segmentAngle" + i));
            String segmentMotionType = request.getParameter("segmentMotionType" + i);

            segments[i] = new Segment(segmentType, segmentAngle, segmentMotionType);
        }

        // Retrieve output preferences
        int resolution = Integer.parseInt(request.getParameter("resolution"));

        // Process the data and calculate cam profile
        List<CamProfileSegment> camProfile = calculateCamProfile(baseCircleRadius, lift, followerType, followerRadius, segments, resolution);

        // Set the cam profile as a request attribute and forward to JSP
        request.setAttribute("camProfile", camProfile);
        request.getRequestDispatcher("/Home.jsp").forward(request, response);
    }

    // Define a Segment class to hold segment data
    private static class Segment {
        String type;
        double angle;
        String motionType;

        Segment(String type, double angle, String motionType) {
            this.type = type;
            this.angle = angle;
            this.motionType = motionType;
        }
    }

    // Define a CamProfileSegment class to hold calculated cam profile data
    public static class CamProfileSegment {
        private final double angle;
        private final double displacement;
        private final double velocity;
        private final double acceleration;

        public CamProfileSegment(double angle, double displacement, double velocity, double acceleration) {
            this.angle = angle;
            this.displacement = displacement;
            this.velocity = velocity;
            this.acceleration = acceleration;
        }

        public double getAngle() {
            return angle;
        }

        public double getDisplacement() {
            return displacement;
        }

        public double getVelocity() {
            return velocity;
        }

        public double getAcceleration() {
            return acceleration;
        }
    }

    private List<CamProfileSegment> calculateCamProfile(double baseCircleRadius, double lift, String followerType, double followerRadius, Segment[] segments, int resolution) {
        List<CamProfileSegment> camProfile = new ArrayList<>();
        
        // Assuming uniform segments for simplicity in this example
        for (int i = 0; i < resolution; i++) {
            double angle = (360.0 / resolution) * i;
            double displacement = lift * Math.sin(Math.toRadians(angle)); // Example calculation, replace with actual logic
            double velocity = (i == 0) ? 0 : (displacement - camProfile.get(i - 1).getDisplacement()) / (360.0 / resolution);
            double acceleration = (i < 2) ? 0 : (velocity - camProfile.get(i - 1).getVelocity()) / (360.0 / resolution);

            camProfile.add(new CamProfileSegment(angle, displacement, velocity, acceleration));
        }
        
        return camProfile;
    }
}
