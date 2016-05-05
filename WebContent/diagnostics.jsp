
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head><title>Diagnostics Page</title></head>
    <body>
        <table>
            <%@ page import="java.util.*" %>
            <%@ page import="java.sql.*" %>
            <%@ page import="javax.naming.*"%>
            <%@page import="javax.sql.DataSource"%>
            <%

                java.sql.Statement s;
                java.sql.ResultSet rs;
                java.sql.PreparedStatement pst;
              
                s = null;
                pst = null;
                rs = null;
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/EventsDB");
                Connection con = ds.getConnection();

                String sql = "select * from events fetch first 10 rows only";
                try {
                    s = con.createStatement();
                    rs = s.executeQuery(sql);
            %>

            <%
                while (rs.next()) {
            %><tr>
                <td><%= rs.getInt("id")%></td>
                <td><%= rs.getDate("event_date")%></td>
                <td><%= rs.getString("event_type")%></td>
                <td><%= rs.getString("event_summary")%></td>
                <td><%= rs.getInt("event_size")%></td>
            </tr>
            <%
                }
            %>

            <%
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (s != null) {
                        s.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }

            %>

    </body>
</html>