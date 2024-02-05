package my.utm.ip.spring_jdbc.services;
import java.util.List;
import org.springframework.stereotype.Service;

import javafx.event.Event;
import my.utm.ip.spring_jdbc.model.User;

@Service
public interface EventService {
    User getUserById(int id);
    my.utm.ip.spring_jdbc.model.Event getEventById1(int id);
    List<my.utm.ip.spring_jdbc.model.Event> getAllEvents();
    my.utm.ip.spring_jdbc.model.Event insertEvent(my.utm.ip.spring_jdbc.model.Event event);
    void deleteEvent(int eventId);
    List<my.utm.ip.spring_jdbc.model.Event> getEventsByPage(int page, int eventsPerPage);
    int getTotalEvents();
    List<my.utm.ip.spring_jdbc.model.Event> getFilteredEventsForPage(String eventName, int page, int eventsPerPage);
    int getTotalFilteredEvents(String eventName);
    List<my.utm.ip.spring_jdbc.model.Event> filterEvents(int year, String[] selectedMonths, int page, int eventsPerPage);
    int getTotalFilteredEvents(int year, String[] selectedMonths);
    my.utm.ip.spring_jdbc.model.Event getEventById(int id);
}
