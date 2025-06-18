package service.custom.impl;

import Entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeRepository;
import service.custom.EmployeeService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository er = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);

    @Override
    public Boolean addEmployee(Employee e1) throws SQLException {
        EmployeeEntity ee= new ModelMapper().map(e1,EmployeeEntity.class);

        return er.add(ee);
    }

    @Override
    public ObservableList getAll() throws SQLException {
        List<EmployeeEntity>  entities =er.getAll();
        ObservableList<Employee>  employeeObservableList= FXCollections.observableArrayList();

        for(EmployeeEntity entity: entities){
            Employee dto = new ModelMapper().map(entity,Employee.class);
            employeeObservableList.add(dto);
        }
        return employeeObservableList;
    }

    @Override
    public Boolean deleteEmployee(String id) throws SQLException {
       return er.delete(id);
    }

    @Override
    public Employee searchById(String id) throws SQLException {
        EmployeeEntity entity= er.searchById(id);
        if(id==null){
            return null;
        }
        return new ModelMapper().map(entity,Employee.class);
    }
}
