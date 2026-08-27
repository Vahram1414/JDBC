package repository.Impl;

import model.Label;
import model.Post;
import repository.PostRepository;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.attribute.AclEntryPermission.DELETE;

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

    private final static String POST_SAVE = "INSERT INTO post(post_id, post_content, post_created, post_updated, List<Label> labels VALUES (?, ?, ?, ?)";
    private final static String POST_UPDATE = "UPDATE post set post_id = ?, where post_content = ?, where post_created = ?, where post_updated = ?";
    private final static String DELETE_BY_ID = "DELETE FROM post where post_id = ?";

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
                    post.setContent(resultSet.getString("post_content"));
                    post.setCreated(resultSet.getString("post_created"));
                    post.setUpdated(resultSet.getString("post_updated"));
                    post.setLabels(new ArrayList<>());
                    postMap.put(postId, post);
                    posts.add(post);
                }

                int labelId = resultSet.getInt("label_id");
                if (!resultSet.wasNull()) {
                    Label label = new Label();
                    label.setId(labelId);
                    label.setName(resultSet.getString("label_name"));
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
        String sql = "INSERT INTO post(post_content, post_created, post_updated) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getCreated());
            preparedStatement.setString(3, post.getUpdated());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Создание поста не удалось, ничего не добавлено");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    post.setId((int) id);
                }
            }
            //TODO: сохранить записи в postLabels
        } catch (SQLException e) {
            System.out.println("IN save exception: " + e.getMessage());
        }
        return post;
    }


    @Override
    public Post update(Post post) {
        String sqlUpdate = "UPDATE post SET post_content = ?, post_updated = ? WHERE post_id = ?";
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getUpdated());
            preparedStatement.setInt(3, post.getId());

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        String sqlDelete = "DELETE FROM post where post_id = ?";
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("IN deleteById exception: " + e.getMessage());
        }
    }
}
