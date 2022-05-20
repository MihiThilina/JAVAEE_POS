package servlet;

import bo.BOFactory;
import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBoImpl;
import dto.CustomerDTO;
import dto.ItemDTO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    ItemBoImpl itemBo = (ItemBoImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.ITEM);

    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        try {
            JsonArrayBuilder allItem =itemBo.getAllItem();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 200);
            response.add("message", "Done");
            response.add("data", allItem.build());
            writer.print(response.build());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String item_code = req.getParameter("item_code");
        String item_Name = req.getParameter("item_Name");
        String pri_ce = req.getParameter("pri_ce");
        String q_ty = req.getParameter("q_ty");

        System.out.println();
        System.out.println(item_code);
        System.out.println(item_Name);
        System.out.println(pri_ce);

        ItemDTO itemDTO = new ItemDTO(item_code,item_Name,Double.parseDouble(pri_ce),Integer.parseInt(q_ty));
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            if(itemBo.addItem(itemDTO)){
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);//201
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());
            }


        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());
            resp.setStatus(HttpServletResponse.SC_OK); //200
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
            resp.setStatus(HttpServletResponse.SC_OK); //200
            e.printStackTrace();
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String item_code = req.getParameter("item_code");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            if(itemBo.deleteItem(item_code)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Successfully Deleted");
                writer.print(objectBuilder.build());
            }else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("data", "Wrong Id Inserted");
                objectBuilder.add("message", "");
                writer.print(objectBuilder.build());
            }
        } catch (SQLException throwables) {
            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            writer.print(objectBuilder.build());

        } catch (ClassNotFoundException e) {
            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data",e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String item_code = jsonObject.getString("item_code");
        String item_Name = jsonObject.getString("item_Name");
        String pri_ce = jsonObject.getString("pri_ce");
        String q_ty = jsonObject.getString("q_ty");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        ItemDTO itemDTO = new ItemDTO(item_code,item_Name,Double.parseDouble(pri_ce),Integer.parseInt(q_ty));

        try {
            if(itemBo.updateItem(itemDTO)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("data","");
                objectBuilder.add("message","Update Done");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
