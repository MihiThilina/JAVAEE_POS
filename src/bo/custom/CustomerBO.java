package bo.custom;

import bo.SuperBo;
import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.SuperDAO;
import dto.CustomerDTO;
import entity.Customer;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBo {

    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    JsonArrayBuilder getAllCustomer() throws SQLException;
    public CustomerDTO getCustomer(String id) throws SQLException;
}
