package my.utm.ip.spring_jdbc.model;

import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private Date birthdate;
    private String email;
    private String add1;
    private String add2;
    private String zipcode;
    private String state;
    private String job;
    private String phone;
    private String matricsNo;

    public User() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.birthdate = null;
        this.email = "";
        this.add1 = "";
        this.add2 = "";
        this.zipcode = "";
        this.state = "";
        this.job = "";
        this.phone = "";
        this.matricsNo = "";
    }

    public User(int id, String username, String password, String fullname, Date birthdate, String email, String add1,
            String add2, String zipcode, String state, String job, String phone, String matricsNo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.email = email;
        this.add1 = add1;
        this.add2 = add2;
        this.zipcode = zipcode;
        this.state = state;
        this.job = job;
        this.phone = phone;
        this.matricsNo = matricsNo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAdd1() {
        return add1;
    }
    public void setAdd1(String add1) {
        this.add1 = add1;
    }
    public String getAdd2() {
        return add2;
    }
    public void setAdd2(String add2) {
        this.add2 = add2;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMatricsNo() {
        return matricsNo;
    }
    public void setMatricsNo(String matricsNo) {
        this.matricsNo = matricsNo;
}
}