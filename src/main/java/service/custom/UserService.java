package service.custom;

import javafx.collections.ObservableList;
import model.Product;
import model.User;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends SuperService {
    Boolean addUser(User u1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteUser(String id)throws SQLException;
    User SearchByUsername(String username)throws SQLException;

    User SearchById(String id)throws SQLException;
    Boolean UpdateUser(User u1) throws SQLException;
    List<String> getUserIds() throws SQLException;
}
