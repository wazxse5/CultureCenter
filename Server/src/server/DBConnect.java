package server;

import java.sql.Connection;
import java.sql.*;
public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    /* Dla wykonania programu stworzona została baza testowa o nazwie test z jedną tabelą "persons",
     trzema kołumnami "Name", "Surname" , "Age" oraz trzema osobami podanymi niżej
     */
    private String driver = "com.mysql.cj.jdbc.Driver";
    //private String url = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET"; // dla testów
    private String url = "jdbc:mysql://localhost/culturecenter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET"; // dla naszej bazy
    private String user = "root";
    private String password = "";
   // private String password = "usbw"; -- do mojej bazy usbwebserver

    public DBConnect() {
        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void getData() {
        try {
            int temp = 0;
            String query = "select * from employee";
            rs = st.executeQuery(query);
            System.out.println("Records from Database:");
            while (rs.next()) {
                /* Dla testów */
                temp++;
                System.out.println("Employee " + temp + ':');
                String idE = rs.getString("idEmployee");
                String name = rs.getString("Name");
                String dp = rs.getString("Department");
                String surname = rs.getString("Surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String salary = rs.getString("Salary");
                String idB = rs.getString("idBranch");
                System.out.println("Name: " + name + ";\t" + "Surname: " + surname + ";\t" + "ID: " + idE + ";\t"
                        + "Department: " + dp + ";\t" + "Login: " + login + ";\t" + "Haslo: " + password + ";\t"
                        + "Salary: " + salary + ";\t" + "Branch ID: " + idB + ";\t");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addEmployee() {
        String query = "CALL addEmployee(\"Oskar\", \"Goerczowski\", \"PK\", \"user\", \"user1\", \"99.99\", 1)";
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addClient(String name, String surname, String mail, String login, String password) throws SQLException {
        String query = "CALL addClient(?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, mail);
        ps.setString(4, login);
        ps.setString(5, password);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("res");
    }

    public int loginUser(String name, String password) throws SQLException {
        String query = "CALL loginClient(?, ?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, password);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("res");
    }

    public ResultSet getLogs(String login)throws SQLException{
        String query = "CALL showLogs(\""+login + "\")";
        rs=st.executeQuery(query);
        rs.next();
        return rs;
    }
    public ResultSet getEvents() throws SQLException{
        String query = "CALL showEvents";
        rs=st.executeQuery(query);
        System.out.println(rs);
        rs.next();
        return rs;
    }
    public ResultSet getRepertuar() throws SQLException{
        String query = "CALL showSchedule";
        rs=st.executeQuery(query);
        System.out.println(rs);
        rs.next();
        return rs;
    }


}
