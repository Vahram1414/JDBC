//package repository.Impl;
//
//import model.Label;
//import model.Writer;
//import repository.WriterRepository;
//import utils.JDBCUtils;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Objects;
//
//
//
//public class JdbcWriterRepositoryImpl implements WriterRepository {
//
//    private List<Writer> writerList = new ArrayList<>();
//
//    private final static String GET_WRITER_BY_ID_QUERY = "SELECT * FROM WHERE writer_id = ?";
//    private final static String GET_WRITER_ALL = "SELECT * FROM writer";
//    private final static String WRITER_SAVE = "INSERT INTO label(writer_id, writer_firstName VALUES (?, ?), " +
//            "writer_lastName VALUES (?, ?)";
//    private final static String WRITER_UPDATE = "UPDATE writerset writer_firstName = ?  writer_lastName = ? where label_id = ?";
//    private final static String DELETE_BY_ID = "DELETE FROM writer where writer_id = ?";
//
//    @Override
//    public Writer getById(Integer integer) {
//        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(GET_WRITER_BY_ID_QUERY)) {
//            preparedStatement.setInt(1, integer);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            return convertResultSetToWriter(resultSet);
//
//        } catch (SQLException e) {
//            System.out.println("IN getById exception " + e.getMessage());
//            return null;
//        }
//
//
//        return null;
//    }
//
//    @Override
//    public List<Writer> getAll() {
//        return null;
//    }
//
//    @Override
//    public Writer save(Writer writer) {
//        return null;
//    }
//
//    @Override
//    public Writer update(Writer writer) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//
//    }
//}
