package repository.Impl;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T, ID> {

    T getById(ID id);

    List<T> getAll() throws SQLException;

    T save (T t);

    T update (T t);

    void deleteById (ID id);
}
