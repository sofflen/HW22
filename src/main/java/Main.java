import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBConnection dbConnection = new DBConnection();

        dbConnection.getAllStudents();
        dbConnection.getStudentsByYear(2015);
        dbConnection.getStudentsByGroup(4);
        dbConnection.getStudentsLessonsMarksAndTeachers(2);
        dbConnection.getStudentsAverage(2);

        dbConnection.closeConnection();

    }

}
