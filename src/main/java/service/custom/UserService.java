package service.custom;

import javafx.collections.ObservableList;
import model.User;
import service.SuperService;

import java.sql.SQLException;

public interface UserService extends SuperService {


    Boolean addUser(User u1) throws SQLException;
    Boolean updateCustomer(User u1);
    User searchById(String id);
    ObservableList getAll() throws SQLException;
}
