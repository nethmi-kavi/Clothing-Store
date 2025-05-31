package service;

import service.custom.impl.ProductServiceImpl;
import service.custom.impl.UserServiceImpl;
import util.ServiceType;

public class ServiceFactory {

    private ServiceFactory(){}
    private  static  ServiceFactory instance;
    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case USER:return(T) new UserServiceImpl();
            case PRODUCT:return(T) new ProductServiceImpl();

        }
        return null;
    }
}
