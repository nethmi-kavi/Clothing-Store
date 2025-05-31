package service.custom.impl;

import javafx.collections.ObservableList;
import model.User;
import service.custom.UserService;
import util.CrudUtil;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public Boolean addUser(User u1) throws SQLException {
        String query = "INSERT INTO employee(name, mobile, username, password,confirm) VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(query, u1.getName(), u1.getMobile(), u1.getUsername(), u1.getPassword(),u1.getConpassword());
    }

    @Override
    public Boolean updateCustomer(User u1) {
        return null;
    }

    @Override
    public User searchById(String id) {
        return null;
    }

    @Override
    public ObservableList getAll() throws SQLException {
        return null;
    }
}
