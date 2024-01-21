package my.utm.ip.spring_jdbc.model;

public class Bill {
    private int id;
    private int userid;
    private String address;
    private int year;
    private int month;
    private double currentConsumption;
    private double carbonFootprint;
    private String billtype;

    public Bill(int id, int userid, String address, int year,int month, double currentConsumption, double carbonFootprint,String billtype) {
        this.id = id;
        this.userid = userid;
        this.address = address;
        this.year = year;
        this.month = month;
        this.currentConsumption = currentConsumption;
        this.carbonFootprint = carbonFootprint;
        billtype=this.billtype;

    }

    public Bill() {
        this.id = 0;
        this.userid = 0;
        this.address = "";
        this.year = 0;
        this.month = 0;
        this.currentConsumption = 0.0;
        this.carbonFootprint = 0.0;
        billtype="";
    }

    public String getbilltype(){
        return billtype;
    }

    public void setbilltype(String billtype){
        this.billtype = billtype;
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

     public int getMonth() {
        return month;
    }
    

    public void setYear(int year) {
        this.year = year;
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", userid=" + userid +
                ", address='" + address + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", currentConsumption=" + currentConsumption +
                ", carbonFootprint=" + carbonFootprint +
                '}';
    }

    public static String getPeriod(int month, int year) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (month >= 1 && month <= 12) {
            return monthNames[month - 1] + " " + year;
        } else {
            // Handle invalid month values
            return "";
        }
}}