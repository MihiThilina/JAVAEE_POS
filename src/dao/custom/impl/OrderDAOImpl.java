package dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.OrderDAO;
import entity.Order;
import servlet.OrderServlet;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = OrderServlet.dataSource.getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT into `Order` values (?,?,?,?)");
        pst.setObject(1,order.getOrderID());
        pst.setObject(2,order.getOrderDate());
        pst.setObject(3,order.getOrderTime());
        pst.setObject(4,order.getCustID());

        int ex = pst.executeUpdate();
        connection.close();

        return  ex>0;
    }

    @Override
    public boolean delete(ID id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JsonObjectBuilder search(ID id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonArrayBuilder getAll() throws SQLException {
        return null;
    }

    @Override
    public Object getCode(String id) throws SQLException {
        return null;
    }
}
