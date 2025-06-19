package repository.Impl;

import model.Label;
import repository.LabelRepository;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    private List<Label> labelList = new ArrayList<>();

    private final static String GET_LABEL_BY_ID_QUERY = "SELECT * FROM WHERE label_id = ?";
    private final static String GET_LABEL_ALL = "SELECT * FROM label";
    private final static String LABEL_SAVE = "INSERT INTO label(writer_id, label_name VALUES (?, ?)";
    private final static String LABEL_UPDATE = "UPDATE labelset label_name = ? where label_id = ?";
    private final static String DELETE_BY_ID = "DELETE FROM label where label_id = ?";

    public Label getById(Integer integer) {
        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(GET_LABEL_BY_ID_QUERY)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();

            return convertResultSetToLabel(resultSet);
        } catch (SQLException e) {
            System.out.println("IN getById exception: " + e.getMessage());
            return null;
        }
    }

    public List<Label> getAll() {
        try(Statement statement = JDBCUtils.getConnectJDBC().createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_LABEL_ALL);

            while (resultSet.next()) {
                Label label = new Label();

                label.setId(resultSet.getInt("label_id"));
                label.setId((resultSet.getInt("writer_id")));
                label.setName(resultSet.getString("label_name"));

                labelList.add(label);

                System.out.println("LabelId: " + label.getId());
                System.out.println("LabelName: " + label.getName());
                System.out.println("=======================================================");
            }
        } catch (SQLException e) {
            System.out.println("IN getAll exception: " + e.getMessage());
        }
        return labelList;
    }

    public Label save(Label label) {
        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(LABEL_SAVE)) {

            preparedStatement.setInt(1, generateNewId(labelList));
            preparedStatement.setString(2, label.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN save exception: " + e.getMessage());
        }
        return label;
    }

    public Label update(Label label) {
        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(LABEL_UPDATE)) {

            preparedStatement.setString(1, label.getName());
            preparedStatement.setInt(2, label.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN update exception: " + e.getMessage());
        }
        return label;
    }

    public void deleteById(Integer integer) {
        try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(DELETE_BY_ID)) {

            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN delete exception " + e.getMessage());
        }
    }

//    private Integer generateNewId(List<Label> labels) {
//        Label labelWithMaxId = (Label)labels.stream().max(Comparator.comparing(Label::getId)).orElse((Label)null);
//        Objects.nonNull(labelWithMaxId);
//        return Objects.nonNull(labelWithMaxId) ? (labelWithMaxId.getId()) + 1 : 1;
//    }

    private Label convertResultSetToLabel(ResultSet resultSet) {
        Label label = null;
        try{
            while (resultSet.next()) {
                label = new Label();
                label.setId(resultSet.getInt("label_id"));
                label.setName(resultSet.getString("label_name"));
            }
        } catch (SQLException e) {
            System.out.println("IN convertResultSetToLabel error " + e.getMessage());
        }
        return label;
    }
}
