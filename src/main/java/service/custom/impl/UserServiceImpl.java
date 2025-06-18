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

public class UserServiceImpl implements UserService {
    UserRepository us = DaoFactory.getInstance().getRepositoryType(RepositoryType.USER);



    @Override
    public Boolean addUser(User u1) throws SQLException {
        UserEntity ue =new ModelMapper().map(u1,UserEntity.class);

        return us.add(ue);
    }

    @Override
    public ObservableList getAll() throws SQLException {
        return null;
    }

    @Override
    public Boolean deleteUser(String id) throws SQLException {
        return null;
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


}
