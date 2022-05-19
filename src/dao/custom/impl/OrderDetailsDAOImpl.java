package dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.OrderDetailsDAO;
import entity.Order_Details;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.SQLException;

public class OrderDetailsDAOImpl  implements OrderDetailsDAO {
    @Override
    public boolean add(Order_Details order_details) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(ID id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order_Details order_details) throws SQLException, ClassNotFoundException {
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
