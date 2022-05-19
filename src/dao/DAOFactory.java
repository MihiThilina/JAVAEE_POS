package dao;

import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM :
                return new ItemDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER,ITEM
    }

}
