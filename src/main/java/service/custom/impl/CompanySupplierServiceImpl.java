package service.custom.impl;

import Entity.CompanySupplierEntity;
import Entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CompanySupplier;
import model.Employee;
import model.Supplier;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CompanySupplierRepository;
import repository.custom.SupplierRepository;
import service.custom.CompanySupplierService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class CompanySupplierServiceImpl implements CompanySupplierService {
    CompanySupplierRepository sr = DaoFactory.getInstance().getRepositoryType(RepositoryType.COMPANYSUPPLIER);

    @Override
    public Boolean addCompanySupplier(CompanySupplier s1) throws SQLException {
        CompanySupplierEntity se= new ModelMapper().map(s1,CompanySupplierEntity.class);

        return sr.add(se);
    }

    @Override
    public ObservableList getAll() throws SQLException {
        List<CompanySupplierEntity> entities =sr.getAll();
        ObservableList<CompanySupplier>  supplierObservableList= FXCollections.observableArrayList();

        for(CompanySupplierEntity entity: entities){
            CompanySupplier dto = new ModelMapper().map(entity,CompanySupplier.class);
            supplierObservableList.add(dto);
        }
        return supplierObservableList;
    }

    @Override
    public Boolean deleteCompanySupplier(String id) throws SQLException {
        return sr.delete(id);
    }

    @Override
    public CompanySupplier searchById(String id) throws SQLException {
        CompanySupplierEntity entity= sr.searchById(id);
        if(id==null){
            return null;
        }
        return new ModelMapper().map(entity,CompanySupplier.class);
    }

    @Override
    public boolean UpdateCompanySupplier(CompanySupplier s1) throws SQLException {
        CompanySupplierEntity se= new ModelMapper().map(s1, CompanySupplierEntity.class);

        return sr.update(se);
    }
}
