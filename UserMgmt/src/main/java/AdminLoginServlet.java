import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        // Set content type
        res.setContentType("text/html");
        // Get the values
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // Simple username and password check (for demonstration purposes only)
        if ("Dams".equals(username) && "Dams@123#".equals(password)) {
            // Mark the user as authenticated by setting a session attribute
            HttpSession session = req.getSession();
            session.setAttribute("adminLoggedIn", true);
            // Redirect to admin home page
            res.sendRedirect("adminHome.html");
        } else {
            pw.println("<h2 class='bg-danger text-light text-center'>Invalid Credentials</h2>");
            pw.println("<a href='login.html' class='btn btn-outline-success'>Try Again</a>");
        }
        // Close the stream
        pw.close();
    }
}
