package repository.custom.impl;

import Entity.CompanySupplierEntity;
import Entity.SupplierEntity;
import repository.custom.CompanySupplierRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanySupplierRepositoryImpl implements CompanySupplierRepository {
    @Override
    public boolean add(CompanySupplierEntity entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO companySupplier (id, name,regNo, mobile, address, email) VALUES (?, ?, ?, ?, ?,?)",
                entity.getId(),entity.getName(),entity.getRegNo(),entity.getMobile(),entity.getAddress(),entity.getEmail());
    }

    @Override
    public boolean update(CompanySupplierEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return CrudUtil.execute("DELETE FROM companySupplier WHERE id=?",s);
    }

    @Override
    public CompanySupplierEntity searchById(String s) throws SQLException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM companySupplier WHERE id=?",s);

        if(rs.next()){
            CompanySupplierEntity ee=new CompanySupplierEntity();
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
    public List<CompanySupplierEntity> getAll() {
        List<CompanySupplierEntity> list =new ArrayList<>();
        try{
            ResultSet rs=CrudUtil.execute("SELECT * FROM companySupplier");
            while (rs.next()){
                CompanySupplierEntity ee=new CompanySupplierEntity();
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
