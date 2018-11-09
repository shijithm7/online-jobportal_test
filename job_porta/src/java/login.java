/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;  
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author multimedia
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    
       @Override
       public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("uname");
        String pass = request.getParameter("psw");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal", "root", "root");
            
            PreparedStatement pst = conn.prepareStatement("Select username,password from login where username=? and password=?");
            
            pst.setString(1, user);
            pst.setString(2, pass);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                response.sendRedirect("search.html");
            } 
            else {
                out.println("Incorrect login credentials");
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
  

       
       
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
