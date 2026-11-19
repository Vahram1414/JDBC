package view;

import controller.LabelController;
import controller.PostController;
import model.Label;
import model.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PostView {
    private final PostController controller = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    public void createPost() {
        System.out.println("Enter post name");
        String name = scanner.nextLine();

    }

    public void updatePost() {
        System.out.println("Enter id Post");
        Integer id = scanner.nextInt();
        System.out.println("Enter content post");
        String content = scanner.nextLine();
        System.out.println("Enter created post");
        String created = scanner.nextLine();
        System.out.println("Enter updated post");
        String updated = scanner.nextLine();
        Post p = controller.updatePost(id, content, created, updated);
        System.out.println("You have entered: " + p);
    }

    public void getAll() throws SQLException {
        List<Post> p = controller.getAll();
    }

    public void getById() {
    }

    public void deleteByIdPost() {
        System.out.println("Enter id Post");
        Integer id = scanner.nextInt();
        System.out.println("Delete post with id: "
                + controller.getById(id)
                + " and name: " + controller.getById(id));
    }



}
