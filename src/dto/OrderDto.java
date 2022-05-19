package dto;

public class OrderDto {
    private String orderID;
    private String  orderDate ;
    private String  orderTime ;
    private String  custID ;

    public OrderDto(String orderID, String orderDate, String orderTime, String custID) {
        this.setOrderID(orderID);
        this.setOrderDate(orderDate);
        this.setOrderTime(orderTime);
        this.setCustID(custID);
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }


}
