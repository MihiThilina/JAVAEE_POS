package entity;

public class Customer {
    private String CustID;
    private String CustName;
    private String CustAddress;
    private double Salary;

    public Customer() {
    }

    public Customer(String custID, String custName, String custAddress, double salary) {
        CustID = custID;
        CustName = custName;
        CustAddress = custAddress;
        Salary = salary;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustID='" + CustID + '\'' +
                ", CustName='" + CustName + '\'' +
                ", CustAddress='" + CustAddress + '\'' +
                ", Salary=" + Salary +
                '}';
    }
}
