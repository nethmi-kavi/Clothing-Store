package repository.custom.impl;

import Entity.EmployeeEntity;
import Entity.SupplierEntity;
import repository.custom.SupplierRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {


    @Override
    public boolean add(SupplierEntity entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO supplier (id, name,regNo, mobile, address, email) VALUES (?, ?, ?, ?, ?,?)",
                entity.getId(),entity.getName(),entity.getRegNo(),entity.getMobile(),entity.getAddress(),entity.getEmail());
    }

    @Override
    public boolean update(SupplierEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM supplier WHERE id=?",s);
    }

    @Override
    public SupplierEntity searchById(String s) throws SQLException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM supplier WHERE id=?",s);

        if(rs.next()){
            SupplierEntity ee=new SupplierEntity();
            ee.setId(rs.getString(1));
            ee.setName(rs.getString(2));
            ee.setRegNo(rs.getString(3));
            ee.setMobile(rs.getString(4));
            ee.setAddress(rs.getString(5));
            ee.setEmail(rs.getString(6));

            return ee;
        }
        return null;
    }

    @Override
    public List<SupplierEntity> getAll() {
        List<SupplierEntity> list =new ArrayList<>();
        try{
            ResultSet rs=CrudUtil.execute("SELECT * FROM supplier");
            while (rs.next()){
                SupplierEntity ee=new SupplierEntity();
                ee.setId(rs.getString(1));
                ee.setName(rs.getString(2));
                ee.setRegNo(rs.getString(3));
                ee.setMobile(rs.getString(4));
                ee.setAddress(rs.getString(5));
                ee.setEmail(rs.getString(6));

                list.add(ee);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
