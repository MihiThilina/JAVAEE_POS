package dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.CustomerDAO;
import entity.Customer;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(ID id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JsonObjectBuilder search(ID id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonArrayBuilder getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
