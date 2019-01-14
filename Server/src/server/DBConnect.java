package server;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private String password = "usbw";
//    private String password = "usbw";//do mojej bazy usbwebserver

    public DBConnect() {
        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT idClient, Name, Surname, mail, login FROM client";
        rs = st.executeQuery(query);
        while (rs.next()) {
            int idClient = rs.getInt("idClient");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String mail = rs.getString("mail");
            String login = rs.getString("login");
            clients.add(new Client(idClient, name, surname, mail, login));
        }
        return clients;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT idEmployee, Name, Surname, Department, login, Salary FROM employee";
        rs = st.executeQuery(query);
        while (rs.next()) {
            int idEmployee = rs.getInt("idEmployee");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String login = rs.getString("login");
            String department = rs.getString("Department");
            float salary = rs.getFloat("Salary");
            employees.add(new Employee(idEmployee, name, surname, login, department, salary));
        }
        return employees;
    }

    public int getClientID(String userLogin) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT idClient FROM client WHERE login = ?");
        st.setString(1, userLogin);
        rs = st.executeQuery();
        if (rs.next()) return rs.getInt(1);
        else return -1;
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

    public String addEmployee(String name, String surname, String department, String login, String password, int salary) throws SQLException {
        String query = "CALL addEmployee(?, ?, ?, ?, ?, ?, 1);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, department);
        ps.setString(4, login);
        ps.setString(5, password);
        ps.setFloat(6, salary);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public String addRoom(Integer number, Integer seats, Integer rows, Integer branchId) throws SQLException {
        String query = "CALL addRoom(?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, number);
        ps.setInt(2, seats);
        ps.setInt(3, rows);
        ps.setInt(4, branchId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    public String changeTicketStatus(String idTicket)throws SQLException{
        String query = "CALL regretTicket(?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,Integer.valueOf(idTicket));
        rs=ps.executeQuery();
        return "Zmieniono dane";
    }


    public ResultSet editEvent(String idEvent, String title, String duration, String ageRestriction, String language, String releaseDate, String type, String imagePath) throws SQLException {
        String query = "CALL editEventType(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, Integer.valueOf(idEvent));
        ps.setString(2, title);
        ps.setTime(3, java.sql.Time.valueOf(duration));
        ps.setInt(4, Integer.valueOf(ageRestriction));
        ps.setString(5, language);
        ps.setDate(6, java.sql.Date.valueOf(releaseDate));
        ps.setString(7, type);
        ps.setString(8, imagePath);
        rs = ps.executeQuery();
//        rs.next();
        return rs;
    }



    public String addInfo(String info) throws SQLException {
        String query = "CALL addInfo(?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, info);
        rs = ps.executeQuery();
        return rs.toString();

    }

    public String addReview(int idEvent, int idUser, int grade, String opinion,String type)throws  SQLException{
        String query = "CALL addReview(?, ?, ?, ?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idEvent);
        ps.setInt(2, idUser);
        ps.setInt(3, grade);
        ps.setString(4, opinion);
        ps.setString(5,type);
        rs = ps.executeQuery();
//        rs.next();
        return "Dodano opinię";
    }

    public ResultSet editRepertoire(String title, String date, String time, String idEventType, String idRoom, String idEvent) throws SQLException {
        String query = "CALL editEvent(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, Integer.valueOf(idEvent));
        ps.setInt(5, Integer.valueOf(idEventType));
        ps.setString(2, title);
        ps.setDate(3, java.sql.Date.valueOf(date));
        ps.setTime(4, java.sql.Time.valueOf(time));
        ps.setInt(6, Integer.valueOf(idRoom));
        rs = ps.executeQuery();
//        rs.next();
        return rs;
    }


    public void addReservation(int idClient, int idEvent, List<Integer> seats, String type, float price, String condition) throws SQLException {
        String query = "CALL buyTicket(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setFloat(1, price);
        ps.setString(2, type);
        ps.setString(3, condition);
        ps.setInt(4, idEvent);
        ps.setInt(6, idClient);
        for (Integer i : seats) {
            ps.setInt(5, i);
            ps.executeUpdate();
        }
    }


    public String addClient(String name, String surname, String mail, String login, String password) throws SQLException {
        String query = "CALL addClient(?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, surname);
//        ps.set
        ps.setString(3, mail);
        ps.setString(4, login);
        ps.setString(5, password);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public ResultSet addRepertoire(String imagePath, String title, String duration, String ageRestriction, String language, String releaseDate, String type) throws SQLException {
        //String query = "CALL addEventType("+imagePath+","+title+","+duration+","+ageRestriction+","+language+","+releaseDate+","+type+");";
        String query = "CALL addEventType(?, ?, ?, ?, ?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, title);
        ps.setTime(3, java.sql.Time.valueOf(duration));
        ps.setInt(4, Integer.valueOf(ageRestriction));
        ps.setString(5, language);
        ps.setDate(6, java.sql.Date.valueOf(releaseDate));
        ps.setString(2, type);
        ps.setString(7, imagePath);
        rs = ps.executeQuery();
        rs.next();
        return rs;
    }

    public ResultSet addEvent(String name, String date, String time, String idEvent, String idRoom) throws SQLException {
        String query = "CALL addEvent(?, ?, ?, ?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setTime(3, java.sql.Time.valueOf(time));
        ps.setDate(2, java.sql.Date.valueOf(date));
        ps.setInt(4, Integer.valueOf(idEvent));
        ps.setInt(5, Integer.valueOf(idRoom));
        rs = ps.executeQuery();
//        rs.next();
        return rs;
    }

    public String loginUser(String name, String password, boolean employee) throws SQLException {
        String query = "";
        if (employee) query = "CALL loginEmployee(?, ?);";
        else query = "CALL loginClient(?, ?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, password);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }



    public String getEmail(String email) throws SQLException {
        String query = "CALL getLogin(\"" + email + "\")";
        rs = st.executeQuery(query);
        rs.next();
        return rs.getString(1);
    }

    public ResultSet getLogs(String login) throws SQLException {
        String query = "CALL showLogs(\"" + login + "\")";
        rs = st.executeQuery(query);
        // rs.next();
        return rs;
    }

    public ResultSet getInfos() throws SQLException {
        String query = "CALL showInfos()";
        rs = st.executeQuery(query);
        // rs.next();
        return rs;
    }


    public ResultSet getEvents() throws SQLException {
        String query = "CALL showEventType()";
        rs = st.executeQuery(query);
        //rs.next();
        return rs;
    }

    public ResultSet getHistory(String idClient) throws SQLException{
        String query = "CALL getHistory(\""+idClient+"\")";
        rs=st.executeQuery(query);
        return rs;
    }

    public ResultSet getIdAndNameOfEvents() throws SQLException {
        String query = "CALL getIdAndNameOfEvents()";
        rs = st.executeQuery(query);
        //rs.next();
        return rs;

    }

    public ResultSet getIdOfRooms() throws SQLException {
        String query = "CALL showRooms()";
        rs = st.executeQuery(query);
        return rs;
    }

    public ResultSet getRepertuar() throws SQLException {
        String query = "CALL showEventsPlusRoom()";
        rs = st.executeQuery(query);
        System.out.println(rs);
        // rs.next();
        return rs;
    }

    public List<Integer> getSeatsId(int eventID) throws SQLException {
        String query = "CALL getSeatsID(?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, eventID);
        rs = ps.executeQuery();
        List<Integer> reserved = new ArrayList<>();
        while (rs.next()) {
            reserved.add(rs.getInt(1));
        }
        return reserved;
    }


    public boolean changeName(String login, String newName) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE client SET Name = (?) WHERE login = (?);");
            ps.setString(1, newName);
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean changeSurname(String login, String newSurname) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE client SET Surname = (?) WHERE login = (?);");
            ps.setString(1, newSurname);
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean changeMail(String login, String newMail) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE client SET mail = (?) WHERE login = (?);");
            ps.setString(1, newMail);
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean changePassword(String login, String currentPassword, String newPassword) {
        // FIXME: Tu by się przydała osobna procedura w bazie
        return false;
    }
}
