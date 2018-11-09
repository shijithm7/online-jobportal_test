/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author multimedia
 */
@WebServlet(urlPatterns = {"/search"})
public class search extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        
        String search = request.getParameter("search");
       // String pass = request.getParameter("psw");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal", "root", "root");
            
            PreparedStatement pst = conn.prepareStatement("Select job,salary,eligibility from job where job=?");
             pst.setString(1, search);
              ResultSet rs = pst.executeQuery();
   
         
            if (rs.next()) {
            String job = rs.getString("job");
            int sal = rs.getInt("salary");
            String eli = rs.getString("eligibility");
             out.println("Job: " + job + "<br>");
            out.println("Salary: " + sal + "<br>");
            out.println("Eligibility: " + eli + "<br>");
;
            } 
            else {
                out.println("Not found");
            }
        } 
    catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
}}
