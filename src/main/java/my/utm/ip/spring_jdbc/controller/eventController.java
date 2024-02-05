package my.utm.ip.spring_jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import my.utm.ip.spring_jdbc.model.Event;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.services.EventService;
import my.utm.ip.spring_jdbc.services.UserService;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
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
    private EventService eventService;
    
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
        List<Event> eventList = eventService.getAllEvents();
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

        if (event.getImageData() != null) {
            String imagedata = Base64.getEncoder().encodeToString(event.getImageData());
            mv.addObject("eventImg", imagedata);
        } else {
            mv.addObject("eventImg", ""); // Set an empty string or some default value
        }

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
                    @RequestParam("event_img") MultipartFile event_img,
                    HttpServletRequest request, HttpSession session) throws IOException {
    
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
        if (!event_img.isEmpty()) {
            byte[] fileBytes = event_img.getBytes();
            event.setImageData(fileBytes);
        }
        event.setUserid(userid);
        

       eventService.insertEvent(event);
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
                    @RequestParam("event_img") MultipartFile event_img,
                    HttpServletRequest request, HttpSession session) throws IOException{
    
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
        
        if (!event_img.isEmpty()) {
            byte[] fileBytes = event_img.getBytes();
            event.setImageData(fileBytes);
        }

        event.setUserid(userid);

        String sql = "UPDATE event SET title=?, start_date=?, end_date=?, location=?, organizer=?, description=?, image_data=?, userid=? WHERE id=?";
        template.update(sql, event.getTitle(), event.getStartDate(), event.getEndDate(), event.getLocation(), event.getOrganizer(), event.getDescription(), event.getImageData(), event.getUserid(), event.getId());

        // Redirect to the event list page
        return "redirect:/event/events";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
    public String deleteEvent(@RequestParam("id") int eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/event/events";
    }
    
    @RequestMapping("/eventlist")
    public ModelAndView eventList(@RequestParam(defaultValue = "1") int page, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        int eventsPerPage = 10;

        int totalEvents = eventService.getTotalEvents();
        int totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        page = Math.max(1, Math.min(page, totalPages));
        List<Event> eventList = eventService.getEventsByPage(page, eventsPerPage);
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
        int totalEvents = eventService.getTotalFilteredEvents(eventName);

        int totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        // Ensure the current page is within valid bounds
        page = Math.max(1, Math.min(page, totalPages));
        List<Event> eventList = eventService.getFilteredEventsForPage(eventName, page, eventsPerPage);
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
        int eventsPerPage =10;
        int userid = (int) session.getAttribute("userid");
        String[] selectedMonths = month.split(",");
        // Retrieve filtered events
        List<Event> eventList = eventService.filterEvents(year, selectedMonths, page, eventsPerPage);
        mv.addObject("eventList", eventList);

        // Recalculate total pages after applying the filter
        int totalEvents = eventService.getTotalFilteredEvents(year, selectedMonths);
        int totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);
        page = Math.max(1, Math.min(page, totalPages));

        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", page);

        return mv;
    }


    @RequestMapping("/event/details/{eventId}")
    public ModelAndView eventDetails(@PathVariable("eventId") int eventId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        User user = eventService.getUserById(userid);

        Event event = eventService.getEventById(eventId);
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
