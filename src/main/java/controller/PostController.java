package controller;

import model.Label;
import model.Post;
import repository.Impl.JdbcLabelRepositoryImpl;
import repository.Impl.JdbcPostRepositoryImpl;
import repository.PostRepository;

import java.sql.SQLException;
import java.util.List;

public class PostController {

    private final PostRepository repo;

    public PostController() {
        repo = new JdbcPostRepositoryImpl();
    }

    public PostController(PostRepository repo) {
        this.repo = repo;
    }

    public Post createPost(String content) {
        Post post = new Post();
        post.setContent(content);
        return repo.save(post);
    }

    public Post updatePost (Integer id, String comtemt, String created, String updated) {
        Post post = new Post();
        post.setId(id);
        post.setContent(comtemt);
        post.setCreated(created);
        post.setUpdated(updated);
        return repo.update(post);
    }

    public List<Post> getAll() throws SQLException {
        return repo.getAll();
    }

    public void deleteByIdPost(Integer id) {
        repo.deleteById(id);
    }

    public Post getById(Integer id) {
        return repo.getById(id);
    }


}
