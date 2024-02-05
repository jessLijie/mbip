package my.utm.ip.spring_jdbc.model.Repository;

import java.util.List;

import my.utm.ip.spring_jdbc.model.Event;
import my.utm.ip.spring_jdbc.model.DAO.EventDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

public interface EventRepository {
    EventDAO getEventById1(int id);

    List<EventDAO> getAllEvents();

    EventDAO insertEvent(EventDAO event);

    void deleteEvent(int eventId);

    List<EventDAO> getEventsByPage(int page, int eventsPerPage);

    int getTotalEvents();

    List<EventDAO> getFilteredEventsForPage(String eventName, int page, int eventsPerPage);

    int getTotalFilteredEvents(String eventName);

    List<EventDAO> filterEvents(int year, String[] selectedMonths, int page, int eventsPerPage);

    int getTotalFilteredEvents(int year, String[] selectedMonths);
    
    EventDAO getEventById(int id);
    
    UserDAO getUserById(int id);
}
