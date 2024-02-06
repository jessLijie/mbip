
package my.utm.ip.spring_jdbc.model.DAO;

import java.util.Arrays;

public class RecycleDAO {
    private int id;
    private int userid;
    private String address;
    private int year;
    private int month;
    private double currentConsumption;
    private double carbonFootprint;
    private byte[] bill_img;

    // Constructor with parameters
    public RecycleDAO(int id, int userid, String address, int year, int month,
                          double currentConsumption, double carbonFootprint, byte[] bill_img) {
        this.id = id;
        this.userid = userid;
        this.address = address;
        this.year = year;
        this.month = month;
        this.currentConsumption = currentConsumption;
        this.carbonFootprint = carbonFootprint;
        this.bill_img = bill_img;
    }

    // Default constructor
    public RecycleDAO() {
        this.id = 0;
        this.userid = 0;
        this.address = "";
        this.year = 0;
        this.month = 0;
        this.currentConsumption = 0.0;
        this.carbonFootprint = 0.0;
        this.bill_img = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getCurrentConsumption() {
        return currentConsumption;
    }

    public void setCurrentConsumption(double currentConsumption) {
        this.currentConsumption = currentConsumption;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }

    public void setCarbonFootprint(double carbonFootprint) {
        this.carbonFootprint = carbonFootprint;
    }

    public byte[] getBillImg() {
        return bill_img;
    }

    public void setBillImg(byte[] bill_img) {
        this.bill_img = bill_img;
    }

    @Override
    public String toString() {
        return "RecycleDAO [id=" + id +
                ", userid=" + userid +
                ", address=" + address +
                ", year=" + year +
                ", month=" + month +
                ", currentConsumption=" + currentConsumption +
                ", carbonFootprint=" + carbonFootprint +
                ", bill_img=" + Arrays.toString(bill_img) + "]";
    }
}
