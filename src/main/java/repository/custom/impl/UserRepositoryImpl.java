package repository.custom.impl;

import Entity.UserEntity;
import model.User;
import repository.custom.UserRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean add(UserEntity entity) throws SQLException {
       return CrudUtil.execute("INSERT INTO user(id, name, mobile, username, password) VALUES (?, ?, ?, ?, ?)",
                entity.getId(),entity.getName(),entity.getMobild(),entity.getUsername(),entity.getPassword());
    }

    @Override
    public boolean update(UserEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public UserEntity searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return List.of();
    }


    @Override
    public UserEntity searchByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";
        ResultSet rs = CrudUtil.execute(sql, username);

        if (rs.next()) {
            return new UserEntity(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("mobile"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }

        return null;

    }
}
