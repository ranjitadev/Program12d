 /*
12d. Develop a JDBC project using MySQL to delete the records in the table Emp of the database
Employee by getting the name starting with ‘S’ through JSP and Generate the report as follows

Salary Report
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Emp_No : 101
Emp_Name: Ramesh&#39;
Basic : 25000
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Emp_No : 102
Emp_Name: Ravi
Basic : 20000
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
….
…
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.sql;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Database Credentials
        String url = "jdbc:mysql://localhost:3306/Employee?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "meri1234"; 

        try {
            // Registering the Driver (Crucial for Servlets)
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            // Step 1: Delete records where Name starts with 'S'
            String deleteSQL = "DELETE FROM Emp WHERE Emp_Name LIKE 'S%'";
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            int deletedRows = ps.executeUpdate();

            // Step 2: Generate the Salary Report
            out.println("<h3>Operation Complete. Records Deleted: " + deletedRows + "</h3>");
            out.println("<pre>"); // Maintain spacing and ASCII formatting
            out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            out.println("Salary Report");
            out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Emp");

            while (rs.next()) {
                out.println("Emp_No   : " + rs.getInt("Emp_NO"));
                out.println("Emp_Name : " + rs.getString("Emp_Name"));
                out.println("Basic    : " + rs.getInt("Basicsalary"));
                out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
            out.println("</pre>");
            out.println("<br><a href='index.jsp'>Back to Main Page</a>");

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error Occurred:</h3>");
            out.println("<p style='color:red;'>" + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}