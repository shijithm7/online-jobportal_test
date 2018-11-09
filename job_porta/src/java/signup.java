/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.*;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author multimedia
 */
@WebServlet(urlPatterns = {"/signup"})
public class signup extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("uname");
        String pass = request.getParameter("psw");
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal", "root", "root");
            
          Statement pst=conn.createStatement();
           int i = pst.executeUpdate("insert into login values('"+user+"','"+pass+"')");
           if(i>0)
                out.println("Inserted Successfully");
              else
                out.println("Insert Unsuccessful");
        }
        catch(Exception e)
        {
          out.println(e);       
        }
    }

  
   

}
