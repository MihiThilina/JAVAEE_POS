package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DAOFactory;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

import javax.json.JsonArrayBuilder;
import java.sql.SQLException;

public class ItemBoImpl implements ItemBo {

    ItemDAOImpl itemDAO = (ItemDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public boolean addItem(ItemDTO i) throws SQLException, ClassNotFoundException {
        Item item1 = new Item(i.getItemCode(),i.getItemName(),i.getPrice(),i.getQty());
        return itemDAO.add(item1);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JsonArrayBuilder getAllItem() throws SQLException {
        return itemDAO.getAll();
    }
}
