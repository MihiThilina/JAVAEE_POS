package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DAOFactory;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dto.CustomerDTO;

import javax.json.JsonArrayBuilder;
import java.sql.SQLException;

public class ItemBoImpl implements ItemBo {

    ItemDAOImpl itemDAO = (ItemDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public boolean addItem(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JsonArrayBuilder getAllItem() throws SQLException {
        return null;
    }
}
