package repository.custom.impl;

import Entity.EmployeeEntity;
import repository.custom.EmployeeRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public boolean add(EmployeeEntity entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO employee(id, name, mobile, username, password, confirm) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getId(),entity.getName(),entity.getMobild(),entity.getUsername(),entity.getPassword(),entity.getConPass());
    }

    @Override
    public boolean update(EmployeeEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM employee WHERE id=?",s);
    }

    @Override
    public EmployeeEntity searchById(String s) throws SQLException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM employee WHERE id=?",s);

        if(rs.next()){
            EmployeeEntity ee=new EmployeeEntity();
            ee.setId(rs.getString(1));
            ee.setName(rs.getString(2));
            ee.setMobild(rs.getString(3));
            ee.setUsername(rs.getString(4));
            ee.setPassword(rs.getString(5));

            return ee;
        }
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        List<EmployeeEntity> list =new ArrayList<>();
        try{
            ResultSet rs=CrudUtil.execute("SELECT * FROM employee");
            while (rs.next()){
                EmployeeEntity ee=new EmployeeEntity();
                ee.setId(rs.getString(1));
                ee.setName(rs.getString(2));
                ee.setMobild(rs.getString(3));
                ee.setUsername(rs.getString(4));
                ee.setPassword(rs.getString(5));

                list.add(ee);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       return list;
    }
}
