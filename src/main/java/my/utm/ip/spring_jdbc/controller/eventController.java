package my.utm.ip.spring_jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import my.utm.ip.spring_jdbc.model.Event;
import my.utm.ip.spring_jdbc.model.User;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/event")
public class eventController {
    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/events")
    public ModelAndView allEvents(HttpSession session){
        ModelAndView mv = new ModelAndView("/Event/eventList_Admin");
        
        String sql = "Select id, title, start_date, end_date, location, organizer from event";
        List<Event> eventList = template.query(sql,new BeanPropertyRowMapper<>(Event.class));
        mv.addObject("eventList", eventList);
        return mv;
    }

    @RequestMapping("/eventForm")
    public ModelAndView addEvent(HttpSession session){
        ModelAndView mv = new ModelAndView("/Event/eventForm");
        return mv;
    }

    @RequestMapping("/editEventForm")
    public ModelAndView editEvent(@RequestParam("id") int id, HttpSession session){
        ModelAndView mv = new ModelAndView("/Event/eventEditForm");
        String sql = "SELECT * FROM event WHERE id = ?";
        Event event = template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Event.class));

        mv.addObject("event", event);
        return mv;
    }

    @RequestMapping(value="/addEvent", method=RequestMethod.POST)
    public String insert(@RequestParam("title") String title,
                    @RequestParam("sdate") Date sdate,
                    @RequestParam("edate") Date edate,
                    @RequestParam("location") String location,
                    @RequestParam("organizer") String organizer,
                    @RequestParam("desc") String desc, 
                    @RequestParam("event_img") byte[] event_img,
                    HttpServletRequest request, HttpSession session) {
    
        int userid = (int) session.getAttribute("userid");
    
        String IdSql = "SELECT MAX(id) FROM event";
        Integer lastId = template.queryForObject(IdSql, Integer.class);
        int id = lastId != null ? lastId + 1 : 1;

        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setStartDate(sdate);
        event.setEndDate(edate);
        event.setLocation(location);
        event.setOrganizer(organizer);
        event.setDescription(desc);
        event.setImageData(event_img);
        event.setUserid(userid);
        

        String sql = "INSERT INTO event (id, title, start_date, end_date, location, organizer, description, image_data, userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, event.getId(), event.getTitle(), event.getStartDate(), event.getEndDate(), event.getLocation(), event.getOrganizer(), event.getDescription(), event.getImageData(), event.getUserid());

        // Redirect to the event list page
        return "redirect:/event/events";
    }

    @RequestMapping(value="/editEvent", method=RequestMethod.POST)
    public String update(@RequestParam("eventId") int eventId,
                    @RequestParam("title") String title,
                    @RequestParam("sdate") Date sdate,
                    @RequestParam("edate") Date edate,
                    @RequestParam("location") String location,
                    @RequestParam("organizer") String organizer,
                    @RequestParam("desc") String desc, 
                    @RequestParam("event_img") byte[] event_img,
                    HttpServletRequest request, HttpSession session) {
    
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        Event event = new Event();
        event.setId(eventId);
        event.setTitle(title);
        event.setStartDate(sdate);
        event.setEndDate(edate);
        event.setLocation(location);
        event.setOrganizer(organizer);
        event.setDescription(desc);
        event.setImageData(event_img);
        event.setUserid(userid);

        String sql = "UPDATE event SET title=?, start_date=?, end_date=?, location=?, organizer=?, description=?, image_data=?, userid=? WHERE id=?";
    template.update(sql, event.getTitle(), event.getStartDate(), event.getEndDate(), event.getLocation(), event.getOrganizer(), event.getDescription(), event.getImageData(), event.getUserid(), event.getId());

        // Redirect to the event list page
        return "redirect:/event/events";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
    public String deleteEvent(@RequestParam("id") int eventId) {
        String sql = "DELETE FROM event WHERE id=?";
        template.update(sql, eventId);

        return "redirect:/event/events";
    }
    
    @RequestMapping("/eventlist")
    public ModelAndView eventList(@RequestParam(defaultValue = "1") int page, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        int eventsPerPage = 10;

        int totalEvents = template.queryForObject("SELECT COUNT(*) FROM event", Integer.class);
        int totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        page = Math.max(1, Math.min(page, totalPages));
        int offset = (page - 1) * eventsPerPage;

        String eventSql = "SELECT id, title, start_date, end_date, location, organizer, description, image_data FROM event LIMIT ?, ?";
        List<Event> eventList = template.query(eventSql, new Object[] { offset, eventsPerPage },
                new BeanPropertyRowMapper<>(Event.class));

        ModelAndView modelAndView = new ModelAndView("/Event/eventlist");
        modelAndView.addObject("eventList", eventList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", page);

        return modelAndView;
    }

    @RequestMapping("/FilterEventName")
    public ModelAndView applyFilterEvent(
        @RequestParam("eventName") String eventName,
        @RequestParam(defaultValue = "1") int page,
        HttpSession session) {

        int userid = (int) session.getAttribute("userid");
        int eventsPerPage = 10;

        // Construct the base SQL query
        StringBuilder eventSql = new StringBuilder("SELECT id, title, start_date, end_date, location, organizer, description, image_data FROM event");

        // Check if filtering by eventName
        if (eventName != null && !eventName.isEmpty()) {
            eventSql.append(" WHERE title LIKE ?");
        }

        // Add pagination clauses
        eventSql.append(" LIMIT ?, ?");

        int totalEvents;
        List<Event> eventList;

        if (eventName != null && !eventName.isEmpty()) {
            // If filtering by eventName, calculate total events for the filtered query
            totalEvents = template.queryForObject("SELECT COUNT(*) FROM event WHERE title LIKE ?", new Object[] { "%" + eventName + "%" }, Integer.class);

            // Execute the filtered query with pagination
            eventList = template.query(eventSql.toString(), new Object[] { "%" + eventName + "%", (page - 1) * eventsPerPage, eventsPerPage },
                new BeanPropertyRowMapper<>(Event.class));
        } else {
            // If not filtering, calculate total events for the base query
            totalEvents = template.queryForObject("SELECT COUNT(*) FROM event", Integer.class);

            // Execute the base query with pagination
            eventList = template.query(eventSql.toString(), new Object[] { (page - 1) * eventsPerPage, eventsPerPage },
                new BeanPropertyRowMapper<>(Event.class));
        }

        int totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        // Ensure the current page is within valid bounds
        page = Math.max(1, Math.min(page, totalPages));

        ModelAndView modelAndView = new ModelAndView("/Event/eventlist");
        modelAndView.addObject("eventList", eventList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", page);

        // Pass the filter criteria to the view
        modelAndView.addObject("filterEventName", eventName);

        return modelAndView;
    }


    @RequestMapping("/applyFilterEvent")
    public ModelAndView applyFilter(
            @RequestParam int year,
            @RequestParam String month,
            @RequestParam(defaultValue = "1") int page,
            HttpSession session) {
        
        ModelAndView mv = new ModelAndView("/Event/eventlist");

        int userid = (int) session.getAttribute("userid");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("eventList");

        StringBuilder sql = new StringBuilder(
                "SELECT * FROM event WHERE YEAR(start_date) = ? AND MONTH(start_date) IN (");

        // Add placeholders for the IN clause
        for (int i = 0; i < selectedMonths.length; i++) {
            if (i > 0) {
                sql.append(", ?");
            } else {
                sql.append("?");
            }
        }

        sql.append(") ORDER BY start_date LIMIT ?, ?"); // Add ORDER BY and LIMIT clauses

        int eventsPerPage = 10;
        int offset = (page - 1) * eventsPerPage;

        // Calculate the size of the params array
        int paramsSize = selectedMonths.length + 3;
        Object[] params = new Object[paramsSize];
        params[0] = year;

        // Convert month names to corresponding numeric values
        for (int i = 0; i < selectedMonths.length; i++) {
            params[i + 1] = getMonthNumericValue(selectedMonths[i]);
        }

        params[paramsSize - 2] = offset;
        params[paramsSize - 1] = eventsPerPage;

        List<Event> eventList = template.query(sql.toString(), params, new BeanPropertyRowMapper<>(Event.class));
        mv.addObject("eventList", eventList);

        // Recalculate total pages after applying the filter
        String countSql = "SELECT COUNT(*) FROM event WHERE YEAR(start_date) = ? AND MONTH(start_date) IN ("
                + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")";
        List<Object> countParamsList = new ArrayList<>();
        countParamsList.add(year);

        for (String selectedMonth : selectedMonths) {
            countParamsList.add(getMonthNumericValue(selectedMonth));
        }

        Object[] countParams = countParamsList.toArray();

        int totalEvents = template.queryForObject(countSql, countParams, Integer.class);

        int totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        // Update current page after applying the filter
        page = Math.max(1, Math.min(page, totalPages));

        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", page);

        return mv;
    }

    private int getMonthNumericValue(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return 0; // Invalid month name
        }
    }

    @RequestMapping("/event/details/{eventId}")
    public ModelAndView eventDetails(@PathVariable("eventId") int eventId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        User user = userService.getUserById(userid);

        String eventSql = "SELECT id, title, start_date, end_date, location, organizer, description, image_data FROM event WHERE id=?";
        Event event = template.queryForObject(eventSql, new Object[]{eventId}, new BeanPropertyRowMapper<>(Event.class));

        // Convert image data to Base64 for displaying in HTML
        if (event.getImageData() != null) {
            byte[] base64Encoded = Base64.getEncoder().encode(event.getImageData());
            event.setBase64Image(new String(base64Encoded));
        }

        // Create a ModelAndView and set the view name
        ModelAndView modelAndView = new ModelAndView("/Event/eventDetails");

        // Add the retrieved event details to the model
        modelAndView.addObject("event", event);

        // Add the user's role to the model
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
