package repository.Impl;

import model.Label;
import model.Post;
import repository.PostRepository;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static utils.JDBCUtils.connection;

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
            "p.id = ?";
    private final static String GET_POST_ALL_QUERY2 = "SELECT p.id AS post_id, p.content AS post_content, p.created AS post_created, p.updated " +
            "AS post_updated, l.id AS label_id, l.name AS label_name FROM posts p LEFT JOIN post_labels pl " +
            "ON p.id = pl.post_id LEFT JOIN label l " +
            "ON pl.label_id = l.id";

    private final static String GET_POST_ALL = "SELECT * FROM post";
    private final static String POST_SAVE = "INSERT INTO post(post_id, post_content, post_created, post_updated, List<Label> labels VALUES (?, ?, ?, ?)";
    private final static String POST_UPDATE = "UPDATE postset post_id = ?, where post_content = ?, where post_created = ?, where post_updated = ?";
    private final static String DELETE_BY_ID = "DELETE FROM post where post_id = ?";
    private final static String LIST_LABEL = "SELECT * FROM post_labels where post_id = ? OR label_id = ?";

    @Override
    public Post getById(Integer integer) {

        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(GET_POST_BY_ID)) {
            Post post = new Post();
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = null;
            List<Label> labels = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                {
                    try {
                        post.setId(resultSet.getInt("post_id"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        post.setContent(resultSet.getString("content"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        post.setCreated(resultSet.getString("created"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        post.setUpdated(resultSet.getString("updated"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    int labelId = 0;
                    try {
                        labelId = resultSet.getInt("label_id");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        if (!resultSet.wasNull()) {
                            Label label = new Label();
                            label.setId(labelId);
                            label.setName(resultSet.getString("label_name"));
                            labels.add(label);


                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                post.setLabels(labels);
            }
            return post;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = GET_POST_ALL_QUERY2; // исправил с LEFT JOIN labels на label 12 июня 26г

        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Integer, Post> postMap = new HashMap<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("post_id");
                Post post = postMap.get(postId);
                if (post == null) {
                    post = new Post();
                    post.setId(postId);
                    post.setContent(resultSet.getString("content"));
                    post.setCreated(resultSet.getString("created"));
                    post.setUpdated(resultSet.getString("updated"));
                    post.setLabels(new ArrayList<>());
                    postMap.put(postId, post);
                    posts.add(post);
                }

                int labelId = resultSet.getInt("l.id");
                if (!resultSet.wasNull()) {
                    Label label = new Label();
                    label.setId(labelId);
                    label.setName(resultSet.getString("l.name"));
                    post.getLabels().add(label);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(POST_SAVE)) {

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

