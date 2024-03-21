import java.sql.*;

public class JDBCutil {
    public static void getAllStudent() throws SQLException {
        ResultSet resultSet;
        Connection connection = null;
        String query = "select * from student;";
        try {
            connection = DataConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("id\tname\taddress");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
            }

        } finally {
            connection.close();
        }
    }

    public static void getStudentById(int id) {
        ResultSet resultSet;
        String query = "select id,name,address from student where id = ?";
        try (Connection connection = DataConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println("id\tname\taddress");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception Occured" + e.getMessage());
        }
    }

    public static void creatStudent(Student student) {
        String query = "insert into student values(default,?,?)";
        try {
            Connection connection = DataConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            int affectedRow = preparedStatement.executeUpdate();
            System.out.print(affectedRow > 0 ? "Successfully inserted...!" : "Somthing wrong...!");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStudent(int id, String name, String address) {
        String query = "update student set name = ? , address= ? where id = ?";
        try {
            Connection connection = DataConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, id);
            int affectedRow = preparedStatement.executeUpdate();
            System.out.print(affectedRow > 0 ? "Successfully Updated...!" : "Somthing wrong...!");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteStudent(int id) {
        String query = "delete from student where id = ?";
        try {
            Connection connection = DataConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRow = preparedStatement.executeUpdate();
            System.out.print(affectedRow > 0 ? "Successfully Deleted...!" : "Somthing wrong...!");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
