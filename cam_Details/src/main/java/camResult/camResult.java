import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class CamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve follower specifications
        String followerType = request.getParameter("followerType");
        String followerRadius = request.getParameter("followerRadius");

        // Retrieve cam profile specifications
        String baseCircleRadius = request.getParameter("baseCircleRadius");
        String lift = request.getParameter("lift");

        // Retrieve motion profile
        int numSegments = Integer.parseInt(request.getParameter("numSegments"));
        Segment[] segments = new Segment[numSegments];

        for (int i = 0; i < numSegments; i++) {
            String segmentType = request.getParameter("segmentType" + i);
            String segmentAngle = request.getParameter("segmentAngle" + i);
            String segmentMotionType = request.getParameter("segmentMotionType" + i);

            segments[i] = new Segment(segmentType, segmentAngle, segmentMotionType);
        }

        // Retrieve output preferences
        String resolution = request.getParameter("resolution");

        // Process the data (you can add your processing logic here)
        // For demonstration purposes, print the retrieved values to the console
        System.out.println("Follower Type: " + followerType);
        System.out.println("Follower Radius: " + followerRadius);
        System.out.println("Base Circle Radius: " + baseCircleRadius);
        System.out.println("Lift: " + lift);
        System.out.println("Number of Segments: " + numSegments);
        for (Segment segment : segments) {
            System.out.println("Segment Type: " + segment.type);
            System.out.println("Segment Angle: " + segment.angle);
            System.out.println("Segment Motion Type: " + segment.motionType);
        }
        System.out.println("Resolution: " + resolution);

        // Forward to a JSP to display the results or send a response back
        // request.getRequestDispatcher("/results.jsp").forward(request, response);
    }

    // Define a Segment class to hold segment data
    private static class Segment {
        String type;
        String angle;
        String motionType;

        Segment(String type, String angle, String motionType) {
            this.type = type;
            this.angle = angle;
            this.motionType = motionType;
        }
    }
}
