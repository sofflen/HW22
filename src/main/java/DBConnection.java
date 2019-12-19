import java.sql.*;

public class DBConnection {

    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    private static Statement statement;
    private int a = 1;



    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        statement = connection.createStatement();
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }

    public void getAllStudents() throws SQLException {
        ResultSet set = statement.executeQuery("SELECT first_name, last_name FROM students;");
        while (set.next()) {
            System.out.println(set.getString("first_name") + " " + set.getString("last_name"));
        }
    }

    public void getStudentsByGroup(int group) throws SQLException {
        ResultSet set = statement.executeQuery("select first_name, last_name, name from students" +
                " left join pupilsgroups p on groupname = p.id where p.id = " + group + ";");

        while (set.next()){
            System.out.printf("Name = %s %s, group = %s", set.getString("first_name"),
                    set.getString("last_name"), set.getString("name"));
            System.out.println("");
        }
    }

    public void getStudentsByYear(int year) throws SQLException {
        ResultSet set = statement.executeQuery("SELECT first_name, last_name, year " +
                "FROM students where year = " + year + ";");
        while (set.next()) {
            System.out.printf("Name = %s %s, year = %d", set.getString("first_name"),
                    set.getString("last_name"), set.getInt("year"));
            System.out.println("");
        }
    }

    public void getStudentsLessonsMarksAndTeachers(int id) throws SQLException {
        ResultSet set = statement.executeQuery("select first_name, last_name, mark, l.name, t.name from grades " +
                "left join students s on student = s.id " +
                "left join lessons l on lesson = l.id " +
                "left join teachers t on teacher = t.id where s.id = " + id + ";");
        while (set.next()){
            System.out.printf("Name = %s %s, lesson = %s, teacher = %s, mark = %d",
                    set.getString("first_name"), set.getString("last_name"),
                    set.getString("l.name"), set.getString("t.name"), set.getInt("mark"));
            System.out.println("");
        }
    }

    public void getStudentsAverage(int id) throws SQLException {
        ResultSet set = statement.executeQuery("select first_name, last_name, avg(mark) from grades " +
                "left join students s on student = s.id where s.id = " + id + ";");
        while (set.next()) {
            System.out.printf("Name = %s %s, average mark = %f",
                    set.getString("first_name"), set.getString("last_name"),
                    set.getDouble("avg(mark)"));
        }
    }



}
