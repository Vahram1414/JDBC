import model.Post;
import view.LabelView;
import view.PostView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        LabelView labelView = new LabelView();
        labelView.createLabel();
        PostView postView = new PostView();
        postView.getById();
    }
}
