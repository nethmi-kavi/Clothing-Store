package service.custom.impl;

import Entity.EmployeeEntity;
import Entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Supplier;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeRepository;
import repository.custom.SupplierRepository;
import service.custom.SupplierService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImpl  implements SupplierService {
    SupplierRepository sr = DaoFactory.getInstance().getRepositoryType(RepositoryType.SUPPLIER);
    @Override
    public Boolean addSupplier(Supplier s1) throws SQLException {
        SupplierEntity se= new ModelMapper().map(s1,SupplierEntity.class);

        return sr.add(se);
    }

    @Override
    public ObservableList getAll() throws SQLException {
        List<SupplierEntity> entities =sr.getAll();
        ObservableList<Supplier>  supplierObservableList= FXCollections.observableArrayList();

        for(SupplierEntity entity: entities){
            Supplier dto = new ModelMapper().map(entity,Supplier.class);
            supplierObservableList.add(dto);
        }
        return supplierObservableList;
    }

    @Override
    public Boolean deleteSupplier(String id) throws SQLException {
        return sr.delete(id);
    }

    @Override
    public Supplier searchById(String id) throws SQLException {
        SupplierEntity entity= sr.searchById(id);
        if(id==null){
            return null;
        }
        return new ModelMapper().map(entity,Supplier.class);
    }
}
