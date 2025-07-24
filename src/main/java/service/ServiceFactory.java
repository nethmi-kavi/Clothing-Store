package service;

import model.Employee;
import model.Supplier;
import model.User;
import service.custom.impl.*;
import util.ServiceType;

import static util.RepositoryType.SUPPLIER;

public class ServiceFactory {

    private ServiceFactory(){}
    private  static  ServiceFactory instance;
    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case EMPLOYEE:return(T) new EmployeeServiceImpl();
            case PRODUCT:return(T) new ProductServiceImpl();
            case USER:return(T) new UserServiceImpl();
            case ORDER:return(T) new OrderServiceImpl();
            case SUPPLIER:return(T) new SupplierServiceImpl();

        }
        return null;
    }
}
