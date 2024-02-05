package my.utm.ip.spring_jdbc.model;

import java.sql.Date;
import java.util.Arrays;

import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

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
    private String city;
    private String job;
    private String phone;
    private String matricsNo;
    private String role;
    private byte[] userImg;

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
        this.city = "";
        this.job = "";
        this.phone = "";
        this.matricsNo = "";
        this.role = "";
        this.userImg = null;
    }

    

    public byte[] getUserImg() {
        return userImg;
    }



    public void setUserImg(byte[] userImg) {
        this.userImg = userImg;
    }



    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }



    public User(int id, String username, String password, String fullname, Date birthdate, String email, String add1,
            String add2, String zipcode, String state, String city, String job, String phone, String matricsNo, String role, byte[] userImg) {
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
        this.city = city;
        this.job = job;
        this.phone = phone;
        this.matricsNo = matricsNo;
        this.role = role;
        this.userImg = userImg;
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
    public void setCity(String city) {
        this.city = city;;
    }
    public String getCity() {
        return city;
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", add1='" + add1 + '\'' +
                ", add2='" + add2 + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", matricsNo='" + matricsNo + '\'' +
                ", role='" + role + '\'' +
                ", userImg=" + Arrays.toString(userImg) +
                '}';
    }
    public void fromDAO(UserDAO dao) {
        if (dao != null) {
            this.id = dao.getId();
            this.username = dao.getUsername();
            this.password = dao.getPassword();
            this.fullname = dao.getFullname();
            this.birthdate = dao.getBirthdate();
            this.email = dao.getEmail();
            this.add1 = dao.getAdd1();
            this.add2 = dao.getAdd2();
            this.zipcode = dao.getZipcode();
            this.state = dao.getState();
            this.city = dao.getCity();
            this.job = dao.getJob();
            this.phone = dao.getPhone();
            this.matricsNo = dao.getMatricsNo();
            this.role = dao.getRole();
            this.userImg = dao.getUserImg();
        } else {
            // Handle the case where dao is null (optional)
        }
    }

    public UserDAO toDAO() {
        UserDAO dao = new UserDAO();

        dao.setId(this.id);
        dao.setUsername(this.username);
        dao.setPassword(this.password);
        dao.setFullname(this.fullname);
        dao.setBirthdate(this.birthdate);
        dao.setEmail(this.email);
        dao.setAdd1(this.add1);
        dao.setAdd2(this.add2);
        dao.setZipcode(this.zipcode);
        dao.setState(this.state);
        dao.setCity(this.city);
        dao.setJob(this.job);
        dao.setPhone(this.phone);
        dao.setMatricsNo(this.matricsNo);
        dao.setRole(this.role);
        dao.setUserImg(this.userImg);

        return dao;
    }

    public User(UserDAO dao) {
        this.fromDAO(dao);
    }
}