package servlet;

import bo.BOFactory;
import bo.custom.impl.CustomerBOImpl;
import dto.CustomerDTO;

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

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource dataSource;

    CustomerBOImpl customerBO = (CustomerBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        try {
            JsonArrayBuilder allCustomer = customerBO.getAllCustomer();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 200);
            response.add("message", "Done");
            response.add("data", allCustomer.build());
            writer.print(response.build());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusId = req.getParameter("customerID");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            if(customerBO.deleteCustomer(cusId)){
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

        String customerID = jsonObject.getString("customerID");
        String customerName = jsonObject.getString("customerName");
        String customerAddress = jsonObject.getString("customerAddress");
        String customerSalary = jsonObject.getString("customerSalary");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        CustomerDTO customerDTO = new CustomerDTO(customerID,customerName,customerAddress,Double.parseDouble(customerSalary));

        try {
            if(customerBO.updateCustomer(customerDTO)){
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = req.getParameter("customerID");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String salary = req.getParameter("customerSalary");


        System.out.println(customerID);
        System.out.println(customerName);
        System.out.println(salary);

        CustomerDTO customerDTO = new CustomerDTO(customerID,customerName,customerAddress,Double.parseDouble(salary));
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            if(customerBO.addCustomer(customerDTO)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Updated");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("message", "Update Failed");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }
        } catch (SQLException throwables) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            writer.print(objectBuilder.build());

        } catch (ClassNotFoundException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data",e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }
    }

}



