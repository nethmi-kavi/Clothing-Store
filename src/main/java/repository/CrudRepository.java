package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T,ID> extends SuperRepository{

    boolean add(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean delete(ID id) throws SQLException;
    T searchById(ID id) throws SQLException;
    List<T> getAll();
}
