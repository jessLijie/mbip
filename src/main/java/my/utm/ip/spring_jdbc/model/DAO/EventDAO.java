package my.utm.ip.spring_jdbc.model.DAO;

import java.util.Arrays;
import java.util.Date;

public class EventDAO {
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String location;
    private String organizer;
    private String description;
    private byte[] imageData;
    private String base64Image;
    private int userid;

    public EventDAO(EventDAO dao) {
        // Default constructor
    }

    public EventDAO(int id, String title, Date startDate, Date endDate, String location, String organizer, String description, byte[] imageData) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.organizer = organizer;
        this.description = description;
        this.imageData = imageData;
    }


    public EventDAO() {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", location='" + location + '\'' +
                ", organizer='" + organizer + '\'' +
                ", description='" + description + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                ", base64Image='" + base64Image + '\'' +
                ", userid=" + userid +
                '}';
    }
}
