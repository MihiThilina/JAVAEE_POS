package dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.CustomerDAO;
import entity.Customer;
import servlet.CustomerServlet;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT into Customer values(?,?,?,?)");

        pstm.setObject(1,customer.getCustID());
        pstm.setObject(2,customer.getCustName());
        pstm.setObject(3,customer.getCustAddress());
        pstm.setObject(4,customer.getSalary());

        int executeUpdate = pstm.executeUpdate();
        connection.close();

        return  executeUpdate > 0;

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
