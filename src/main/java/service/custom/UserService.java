package service.custom;

import javafx.collections.ObservableList;
import model.Product;
import model.User;
import service.SuperService;

import java.sql.SQLException;

public interface UserService extends SuperService {
    Boolean addUser(User u1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteUser(String id)throws SQLException;
    User SearchByUsername(String username)throws SQLException;
}
