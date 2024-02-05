package my.utm.ip.spring_jdbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import my.utm.ip.spring_jdbc.model.Event;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.DAO.EventDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;
import my.utm.ip.spring_jdbc.model.Repository.EventRepository;

@Service
public class EventServiceDatabaseImpl implements EventService {

    @Autowired
    EventRepository repo;

    @Override
    public Event getEventById(int id) {
        EventDAO dao = repo.getEventById(id);
        return new Event(dao);
    }

    @Override
    public List<Event> getAllEvents() {
        List<EventDAO> daos = repo.getAllEvents();
        List<Event> eventList = new ArrayList<>();
        for (EventDAO dao : daos) {
            eventList.add(new Event(dao));
        }
        return eventList;
    }

    @Override
    public Event insertEvent(Event event) {
        EventDAO dao = repo.insertEvent(event.toDAO());
        return new Event(dao);
    }

    @Override
    public void deleteEvent(int eventId) {
        repo.deleteEvent(eventId);
    }

    @Override
    public List<Event> getEventsByPage(int page, int eventsPerPage) {
        List<EventDAO> daos = repo.getEventsByPage(page, eventsPerPage);
        List<Event> eventList = new ArrayList<>();
        for (EventDAO dao : daos) {
            eventList.add(new Event(dao));
        }
        return eventList;
    }

    @Override
    public int getTotalEvents() {
        return repo.getTotalEvents();
    }

    @Override
    public List<Event> getFilteredEventsForPage(String eventName, int page, int eventsPerPage) {
        List<EventDAO> daos = repo.getFilteredEventsForPage(eventName, page, eventsPerPage);
        List<Event> eventList = new ArrayList<>();
        for (EventDAO dao : daos) {
            eventList.add(new Event(dao));
        }
        return eventList;
    }

    @Override
    public int getTotalFilteredEvents(String eventName) {
        return repo.getTotalFilteredEvents(eventName);
    }

    @Override
    public List<Event> filterEvents(int year, String[] selectedMonths, int page, int eventsPerPage) {
        List<EventDAO> daos = repo.filterEvents(year, selectedMonths, page, eventsPerPage);
        List<Event> eventList = new ArrayList<>();
        for (EventDAO dao : daos) {
            eventList.add(new Event(dao));
        }
        return eventList;
    }

    @Override
    public int getTotalFilteredEvents(int year, String[] selectedMonths) {
        return repo.getTotalFilteredEvents(year, selectedMonths);
    }

    @Override
    public Event getEventById1(int id) {
        EventDAO dao = repo.getEventById1(id);
        return new Event(dao);
    }
    @Override
    public User getUserById(int id) {
        UserDAO dao = repo.getUserById(id);
        return new User();
       
    }
}
