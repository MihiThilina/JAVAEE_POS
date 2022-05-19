package dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;
import servlet.CustomerServlet;
import servlet.ItemServlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDAOImpl  implements ItemDAO {


    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT into Item values(?,?,?,?)");

        pstm.setObject(1, item.getItemCode());
        pstm.setObject(2, item.getItemName());
        pstm.setObject(3, item.getPrice());
        pstm.setObject(4, item.getQty());

        int executeUpdate = pstm.executeUpdate();
        connection.close();

        return executeUpdate > 0;
    }

    @Override
    public boolean delete(ID id) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from Item where ItemCode=?");
        preparedStatement.setObject(1, id);

        int excuteupdate = preparedStatement.executeUpdate();
        connection.close();
        return excuteupdate > 0;
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Update Customer set  CustName=?,CustAddress=?,Salary=? where CustID=?");
        preparedStatement.setObject(1, i.getItemCode());
        preparedStatement.setObject(2, i.getItemName());
        preparedStatement.setObject(3, i.getPrice());
        preparedStatement.setObject(4, i.getQty());
        int executeUpdate = preparedStatement.executeUpdate();
        connection.close();
        return executeUpdate > 0;
    }

    @Override
    public JsonObjectBuilder search(ID id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonArrayBuilder getAll() throws SQLException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Connection connection = ItemServlet.dataSource.getConnection();
        PreparedStatement pts = connection.prepareStatement("select * from Item");
        ResultSet rst = pts.executeQuery();
        while (rst.next()) {
            String itemCode = rst.getString(1);
            String itemName = rst.getString(2);
            double price = rst.getDouble(3);
            int qty = rst.getInt(4);

            objectBuilder.add("itemCode", itemCode);
            objectBuilder.add("itemName", itemName);
            objectBuilder.add("price", price);
            objectBuilder.add("qty", qty);
            arrayBuilder.add(objectBuilder.build());
        }
        connection.close();
        return arrayBuilder;
    }
}