package com.example.myproj.servlets;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/db")
public class DBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message;
        Connection con = null;
        String connectionString = "jdbc:mysql://localhost:3306"   // Размещение БД
                + "?useUnicode=true&characterEncoding=UTF-8"             // Кодировка канала (подключения)
                + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ;
        // При проблемах согласования времени
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection(connectionString,
                    "botadd_person",
                    "botadd_secret");

            message = "Connection OK" ;
        } catch( Exception ex ) {
            message = ex.getMessage() ;
        }


        req.setAttribute("dbMessage", message);
        if (con != null) {
            String query = req.getParameter("query");

            if ("create".equals(query)) {
                String queryMessage;
                try (Statement statement = con.createStatement() ) {
                    statement.executeUpdate(
                            "CREATE TABLE Users(" +
                                    "id CHAR(36) PRIMARY KEY ," +
                                    "login VARCHAR(32)," +
                                    "passw CHAR(64)," +
                                    "salt CHAR(64))"
                    );
                    queryMessage = "Query OK";
                } catch (SQLException ex) {
                    queryMessage = ex.getMessage();

                }
                req.setAttribute("querymessage", queryMessage);

                req.getRequestDispatcher("WEB-INF/db.jsp").forward(req, resp);
            }
        }

    }
    public void removeUser(String username)
    {

        try {
            Connection con;
            PreparedStatement pstmnt = con.prepareStatement("DELETE FROM user_info WHERE username = ?");
            pstmnt.setString(1, username);
            pstmnt.executeUpdate();

            pstmnt = con.prepareStatement("DELETE FROM users WHERE username = ?");
            pstmnt.setString(1, username);
            pstmnt.executeUpdate();
            pstmnt = con.prepareStatement("DELETE FROM users WHERE username = ?");
            pstmnt.setString(1, username);
            int rows = pstmnt.executeUpdate();
            if (rows == 0) {
                //record does not exist 
                System.out.println("User does not exist");
            } else if (rows > 0) {
                //there were # of rows deleted
                System.out.println(rows + " User records deleted");

            }

            //pstmnt.executeBatch();
            System.out.println("Removed User :" + username);
        } catch (SQLException e) {System.out.println("Error: " + e.getMessage()); }
    }
}

