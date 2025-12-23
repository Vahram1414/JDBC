package repository.Impl;

import model.Post;
import repository.PostRepository;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {

    private List<Post> postList = new ArrayList<>();

    private final static String GET_POST_BY_ID = "SELECT " +
            "    p.id AS post_id," +
            "    p.content," +
            "    p.created," +
            "    p.updated," +
            "    l.id AS label_id," +
            "    l.name AS label_name " +
            "FROM " +
            "    Posts p " +
            "LEFT JOIN " +
            "  post_labels pl ON p.id = pl.post_id " +
            "LEFT JOIN " +
            "    Label l ON pl.label_id = l.id " +
            "WHERE " +
            "    p.id = ?";
    private final static String GET_POST_ALL_QUERY2 = "SELECT p.id AS post_id, p.content AS post_content, p.created AS post_created, p.updated \n" +
            "AS post_updated, l.id AS label_id, l.name AS label_name FROM posts p LEFT JOIN post_labels pl \n" +
            "ON p.id = pl.post_id LEFT JOIN label l\n" +
            "ON pl.label_id = l.id";

    private final static String GET_POST_ALL = "SELECT * FROM post";
    private final static String POST_SAVE = "INSERT INTO post(post_id, post_content, post_created, post_updated, List<Label> labels VALUES (?, ?, ?, ?)";
    private final static String POST_UPDATE = "UPDATE postset post_id = ?, where post_content = ?, where post_created = ?, where post_updated = ?";
    private final static String DELETE_BY_ID = "DELETE FROM post where post_id = ?";
    private final static String LIST_LABEL = "SELECT * FROM post_labels where post_id = ? OR label_id = ?";

    @Override
    public Post getById(Integer integer) {
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(GET_POST_BY_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Post> getAll() throws SQLException {
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(GET_POST_ALL_QUERY2)) {
            ResultSet resultSet = preparedStatement.executeQuery(GET_POST_ALL_QUERY2);
            while (resultSet.next()) {

                Post post = new Post();

                post.setId(resultSet.getInt("post_id"));
                post.setContent((resultSet.getString("post_content")));
                post.setCreated((resultSet.getString("post_created")));
                post.setUpdated((resultSet.getString("post_updated")));

                postList.add(post);

                List<Integer> labelList = new ArrayList<>();
                labelList.add(resultSet.getInt("label_id"));

                System.out.println("PostId: " + post.getId());
                System.out.println("PostContent: " + post.getContent());
                System.out.println("PostCreated: " + post.getCreated());
                System.out.println("PostUpdated: " + post.getUpdated());
                System.out.println("=======================================================");
            }
        }
        return null;
    }

        @Override
        public Post save(Post post) {
            try(PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(POST_SAVE)) {

                preparedStatement.setString(2, post.getContent());
                preparedStatement.setString(3, post.getCreated());
                preparedStatement.setString(4, post.getUpdated());
            } catch (SQLException e) {
                System.out.println("IN save exception: " + e.getMessage());
            }
            return null;
        }


    @Override
    public Post update(Post post) {


        return null;
    }

    @Override
    public void deleteById(Integer integer) {

        }
}

