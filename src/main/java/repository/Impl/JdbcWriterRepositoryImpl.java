package repository.Impl;

import model.Writer;
import repository.WriterRepository;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    private final static String GET_BY_ID = "SELECT * FROM writer LEFT JOIN post p on writer.post_id = p.post_id WHERE writer_id = ?";
    private final static String GET_WRITER_ALL = "SELECT * FROM writer LEFT JOIN post p on writer.post_id = p.post_id";
    private final static String GET_WRITER_SAVE = "INSERT INTO writer(first_name, last_name) VALUES(?, ?)";
    private final static String UPDATE_WRITER = "UPDATE writer SET first_name = ?, last_name = ? WHERE writer_id = ?";
    private final static String DELETE_WRITER = "DELETE FROM writer WHERE writer_id = ?";

    @Override
    public Writer getById(Integer integer) {
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatment(GET_BY_ID)) {

            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();

            return COnvertResultSetToWriter(resultSet);

        } catch (SQLException e) {
            System.out.println("IN ger by id exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writerList = new ArrayList<>();

        try(Statement statement = JDBCUtils.getConnectJDBC().createStatment()) {
            ResultSet resultSet = statement.executeQuery(GET_WRITER_ALL);

            while (resultSet.next()) {
                Writer writer = new Writer();

                writer.setId(resultSet.getInt(writer_id));
                writer.setFirstName(resultSet.getString("first_name"));
                writer.getLastName(resultSet.getString("last_name"));

                writerList.add(writer);

                List<String> postList = new ArrayList<>();

                postList.add(resultSet.getString("post_content"));

                System.out.println("id: " + writer.getId());
                System.out.println("firstName: " + writer.getFirstName());
                System.out.println("lastName: " + writer.getLastName());
                System.out.println("post: " + postList.toString());
                System.out.println("==============================================");
            }
        } catch (SQLException e) {
            System.out.println("IN exception: " + e.getMessage());
        }
        return writerList;
    }

    @Override
    public Writer save(Writer writer) {

        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatment(GET_WRITER_SAVE)) {
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN exception: " + e.getMessage());
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStament(UPDATE_WRITER)) {
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2,writer.getLastName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN exception: " + e.getMessage());
        }
        return writer;
    }

    @Override
    public void deleteById(Integer integer) {
        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStament(DELETE_WRITER)) {
            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN exception: " + e.getMessage());
        }
    }

    private Writer convertResultSetToWriter(ResultSet resultSet) {
        Writer writer = null;

        try{
            while (resultSet.next()) {
                writer = new Writer();
                writer.setId(resultSet.getInt("writer_id"));
                writer.setFirstName(resultSet.getString("first_name"));
                writer.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            System.out.println("IN convertResultSetToWriter error: " + e.getMessage());
        }
        return writer;
    }
}
