package repository.Impl;

import model.Post;
import repository.PostRepository;
import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcPostRepository implements PostRepository{

    private final static String GET_POST_BY_ID_QUERY = "SELECT * FROM post WHERE post_id = ?";
    private final static String GET_POST_BY_ID_QUERY2 = "SELECT p.id AS post_id, p.content AS post_content, p.created AS post_created, p.updated \n" +
            "AS post_updated, l.id AS label_id, l.name AS label_name FROM posts p LEFT JOIN post_labels pl \n" +
            "ON p.id = pl.post_id LEFT JOIN label l\n" +
            "ON pl.label_id = l.id";

    private final static String GET_POST_ALL = "SELECT * FROM post";
    private final static String POST_SAVE = "INSERT INTO post(post_id, post_content, post_created, post_updated, List<Label> labels VALUES (?, ?, ?, ?)";
    private final static String LABEL_UPDATE = "UPDATE postset post_id = ?, where post_content = ?, where post_created = ?, where post_updated = ?";
    private final static String DELETE_BY_ID = "DELETE FROM post where post_id = ?";
    private final static String LIST_LABEL = "SELECT * FROM post_labels where post_id = ? OR label_id = ?";

    @Override
    public Post getById(Integer integer) {
        try (PreparedStatement preparedStatement = JDBCUtils.getConnectJDBC().prepareStatement(GET_POST_BY_ID_QUERY2)
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post save(Post post) {
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
