package entity;

public class Item {
    private String ItemCode;
    private String ItemName;
    private double Price;
    private int  Qty;

    public Item() {
    }

    public Item(String itemCode, String itemName, double price, int qty) {
        ItemCode = itemCode;
        ItemName = itemName;
        Price = price;
        Qty = qty;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Price=" + Price +
                ", Qty=" + Qty +
                '}';
    }
}
