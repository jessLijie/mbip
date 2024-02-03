package my.utm.ip.spring_jdbc.model;

public class Electricity {
    private int id;
    private int userid;
    private String address;
    private String date;
    private double currentConsumption;
    private double carbonFootprint;

    public Electricity(int id, int userid, String address, String date, double currentConsumption,
            double carbonFootprint) {
        this.id = id;
        this.userid = userid;
        this.address = address;
        this.date = date;
        this.currentConsumption = currentConsumption;
        this.carbonFootprint = carbonFootprint;

    }

    public Electricity() {
        this.id = 0;
        this.userid = 0;
        this.address = "";
        this.date = "";
        this.currentConsumption = 0.0;
        this.carbonFootprint = 0.0;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Electricity{" +
                "id=" + id +
                ", userid=" + userid +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", currentConsumption=" + currentConsumption +
                ", carbonFootprint=" + carbonFootprint +
                '}';
    }
}