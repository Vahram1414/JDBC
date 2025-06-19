package view;

import controller.LabelController;
import model.Label;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final LabelController controller = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    public void createLabel() {
        System.out.println("Enter name label");
        String name = scanner.nextLine();
        Label l = controller.createLabel(name);
    }

    public void updateLabel() {
        System.out.println("Enter id Label");
        Integer id = scanner.nextInt();
        System.out.println("Enter name label");
        String name = scanner.nextLine();
        Label l = controller.updateLabel(id, name);
        System.out.println("You have entered: " + l);
    }

    public void getAll() {
        List<Label> l = controller.getAll();
    }

    public void getById() throws SQLException {
        System.out.println("Enter id label");
        Integer id = scanner.nextInt();
        Label l = controller.getById(id);
        System.out.println(l.toString());
    }

    public void deleteByIdLabel() {
        System.out.println("Enter id Label");
        Integer id = scanner.nextInt();
        System.out.println("Delete label with id: "
            + controller.getById(id).getId()
            + " and name: " + controller.getById(id).getName());
    }
}
