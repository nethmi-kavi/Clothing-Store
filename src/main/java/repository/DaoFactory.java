package repository;

import model.Employee;
import repository.custom.impl.*;
import util.RepositoryType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory() :instance;
    }

    public <T extends SuperRepository>T getRepositoryType(RepositoryType type){
        switch (type){
            case EMPLOYEE:return(T) new EmployeeRepositoryImpl();
            case PRODUCT:return(T) new ProductRepositoryImpl();
            case USER:return(T) new UserRepositoryImpl();
            case ORDER:return(T) new OrderRepositoryImpl();
            case ORDERDETAIL:return(T) new OrderDetailRepositoryImpl();

        }
        return null;
    }
}
