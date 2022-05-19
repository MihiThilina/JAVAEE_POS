package dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;
import servlet.ItemServlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl  implements ItemDAO {


    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT into Item values(?,?,?,?)");

        pstm.setString(1, item.getItemCode());
        pstm.setString(2, item.getItemName());
        pstm.setDouble(3, item.getPrice());
        pstm.setInt(4, item.getQty());

        System.out.println(item.getItemCode());

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
        preparedStatement.setString(1, i.getItemCode());
        preparedStatement.setString(2, i.getItemName());
        preparedStatement.setDouble(3, i.getPrice());
        preparedStatement.setInt(4, i.getQty());
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

    @Override
    public Object getCode(String id) throws SQLException {
        return null;
    }


}
