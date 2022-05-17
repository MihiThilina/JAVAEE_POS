package dao;

import dao.custom.impl.CustomerDAOImpl;

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
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER
    }

}
