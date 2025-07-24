package service.custom.impl;

import Entity.EmployeeEntity;
import Entity.UserEntity;
import javafx.collections.ObservableList;
import model.Employee;
import model.Product;
import model.User;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserRepository;
import service.custom.UserService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository us = DaoFactory.getInstance().getRepositoryType(RepositoryType.USER);



    @Override
    public Boolean addUser(User u1) throws SQLException {
        UserEntity ue =new ModelMapper().map(u1,UserEntity.class);

        return us.add(ue);
    }

    @Override
    public ObservableList<User> getAll() throws SQLException {
        List<UserEntity> userEntities = us.getAll();
        ObservableList<User> userList = javafx.collections.FXCollections.observableArrayList();
        ModelMapper mapper = new ModelMapper();
        for (UserEntity ue : userEntities) {
            userList.add(mapper.map(ue, User.class));
        }
        return userList;
    }
    @Override
    public Boolean deleteUser(String id) throws SQLException {
        return us.delete(id);
    }

    @Override
    public User SearchByUsername(String username) throws SQLException {
        UserEntity entity= us.searchByUsername(username);
        if (username == null || username.isBlank()) {
            return null;
        }


        if (entity == null) {
            return null;
        }
        return new ModelMapper().map(entity,User.class);
    }



    @Override
    public User SearchById(String id) throws SQLException {
        if (id == null || id.isBlank()) return null;
        UserEntity entity = us.searchById(id);
        if (entity == null) return null;
        return new ModelMapper().map(entity, User.class);
    }

    @Override
    public Boolean UpdateUser(User u1) throws SQLException {
        UserEntity ue = new ModelMapper().map(u1,UserEntity.class);
        return us.update(ue);
    }

    @Override
    public Boolean UpdateUserPassword(String id,String password) throws SQLException {

        return us.updateUserPassword(id, password);
    }

    @Override
    public List<String> getUserIds() throws SQLException {
        List<User> all= getAll();
        ArrayList<String> userList = new ArrayList<>();
        all.forEach(user->userList.add(user.getId()));

        return userList;
    }


}
