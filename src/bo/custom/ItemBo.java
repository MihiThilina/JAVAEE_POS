package bo.custom;

import bo.SuperBo;
import dto.CustomerDTO;

import javax.json.JsonArrayBuilder;
import java.sql.SQLException;

public interface ItemBo extends SuperBo {
    boolean addItem(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    boolean updateItem(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    JsonArrayBuilder getAllItem() throws SQLException;
}
