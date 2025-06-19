package controller;

import model.Label;
import repository.Impl.JdbcLabelRepositoryImpl;
import repository.LabelRepository;

import java.sql.SQLException;
import java.util.List;

public class LabelController {
    private final LabelRepository repo;

    public LabelController() {
        repo = new JdbcLabelRepositoryImpl();
    }

    public LabelController (LabelRepository repo) {
        this.repo = repo;
    }

    public Label createLabel(String name) {
        Label label = new Label();
        label.setName(name);
        return repo.save(label);
    }

    public Label updateLabel(Integer id, String name) {
        Label label = new Label();
        label.setId(id);
        label.setName(name);
        return repo.update(label);
    }

    public List<Label> getAll() {
        return repo.getAll();
    }

    public void deleteByIdLabel(Integer id) {
        repo.deleteById(id);
    }

    public Label getById(Integer id) {
        return repo.getById(id);
    }
}