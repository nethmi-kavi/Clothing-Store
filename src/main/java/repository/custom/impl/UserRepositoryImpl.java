package repository.custom.impl;

import Entity.ProductEntity;
import Entity.UserEntity;
import model.User;
import repository.custom.UserRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean add(UserEntity entity) throws SQLException {
       return CrudUtil.execute("INSERT INTO user(id, name, mobile, username, password) VALUES (?, ?, ?, ?, ?)",
                entity.getId(),entity.getName(),entity.getMobild(),entity.getUsername(),entity.getPassword());
    }

    @Override
    public boolean update(UserEntity entity) throws SQLException {
        return CrudUtil.execute("UPDATE user SET name = ?, mobile = ?, username = ?, password = ? WHERE id = ?",entity.getName(), entity.getMobild(), entity.getUsername(), entity.getPassword(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM user WHERE id =?",id);
    }

    @Override
    public UserEntity searchById(String id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id=?";
        ResultSet rs = CrudUtil.execute(sql, id);

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

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> list =new ArrayList<>();
        try{
            ResultSet rs=CrudUtil.execute("SELECT * FROM user");
            while (rs.next()){
                UserEntity pe =new UserEntity();
                pe.setId(rs.getString(1));
                pe.setName(rs.getString(2));
                pe.setMobild(rs.getString(3));
                pe.setUsername(rs.getString(4));
                pe.setPassword(rs.getString(5));


                list.add(pe);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
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

    @Override
    public UserEntity searchByUsernameandId(String id, String username) throws SQLException {
        return null;
    }

    @Override
    public Boolean updateUserPassword(String id, String password) throws SQLException {
        return CrudUtil.execute("UPDATE user SET password = ? WHERE id = ?", password, id);
    }
}
