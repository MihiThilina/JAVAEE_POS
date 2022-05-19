package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.impl.CustomerDAOImpl;
import dto.CustomerDTO;
import entity.Customer;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(c.getCustID(),c.getCustName(),c.getCustAddress(),c.getSalary());
        return customerDAO.add(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JsonArrayBuilder getAllCustomer() throws SQLException {
        return customerDAO.getAll();
    }


}
