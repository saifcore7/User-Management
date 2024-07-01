import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {
    private final static String updateQuery = "UPDATE user SET name=?, email=?, mobile=?, dob=?, city=?, gender=? WHERE id=?";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        // Set content type
        res.setContentType("text/html");
        //link 
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        
        // Get parameters from request
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");
        
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt", "root", "root");
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            
            // Set values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            ps.setInt(7, id);
            
            // Execute update
            int result = ps.executeUpdate();
            if (result == 1) {
                pw.println("<h2 class='bg-success text-light text-center'>User updated successfully</h2>");
            } else {
                pw.println("<h2 class='bg-danger text-light text-center'>Error updating user</h2>");
            }
        } catch (SQLException se) {
            pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Link back to home
        pw.println("<a href='home.html' class='btn btn-outline-success'>Home</a>");
        pw.println("&nbsp; &nbsp;");
        pw.println("<a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>");
        // Close the stream
        pw.close();
    }
}
