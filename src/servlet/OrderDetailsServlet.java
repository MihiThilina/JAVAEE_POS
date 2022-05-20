package servlet;

import bo.BOFactory;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderDetailsBoImpl;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orderDetails")
public class OrderDetailsServlet extends HttpServlet {

    OrderDetailsBoImpl orderDetailsBo = (OrderDetailsBoImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.ORDER_DETAILS);


    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        try {
            JsonArrayBuilder allItem = orderDetailsBo.getAllOrderDetails();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 200);
            objectBuilder.add("message", "Done");
            objectBuilder.add("data", allItem.build());
            writer.print(objectBuilder.build());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
