package my.utm.ip.spring_jdbc.model.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.events.Event;
import my.utm.ip.spring_jdbc.model.DAO.EventDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

@Repository
public class EventRepository_JDBC implements EventRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public EventDAO getEventById1(int id) {
        String sql = "SELECT * FROM event WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(EventDAO.class));
    }

    @Override
    public List<EventDAO> getAllEvents() {
        String sql = "SELECT * FROM event";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EventDAO.class));
    }

    @Override
    public EventDAO insertEvent(EventDAO event) {
        EventDAO added = null;
        String sql = "INSERT INTO event (title, start_date, end_date, location, organizer, description, image_data, userid) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                Object[] arg= {event.getTitle(), event.getStartDate(), event.getEndDate(),
                event.getLocation(), event.getOrganizer(), event.getDescription(), event.getImageData(), event.getUserid()
            };

                jdbcTemplate.update(sql, arg);

                return added;
    }

    @Override
    public void deleteEvent(int eventId) {
        String sql = "DELETE FROM event WHERE id=?";
        jdbcTemplate.update(sql, eventId);
    }

    @Override
    public List<EventDAO> getEventsByPage(int page, int eventsPerPage) {
        int offset = (page - 1) * eventsPerPage;
        String sql = "SELECT id, title, start_date, end_date, location, organizer, description, image_data FROM event " +
                "LIMIT ?, ?";
        return jdbcTemplate.query(sql, new Object[]{offset, eventsPerPage}, new BeanPropertyRowMapper<>(EventDAO.class));
    }

    @Override
    public int getTotalEvents() {
        String sql = "SELECT COUNT(*) FROM event";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<EventDAO> getFilteredEventsForPage(String eventName, int page, int eventsPerPage) {
        int offset = (page - 1) * eventsPerPage;
        StringBuilder eventSql = new StringBuilder("SELECT id, title, start_date, end_date, location, organizer, description, image_data FROM event");
        
        // Check if filtering by eventName
        if (eventName != null && !eventName.isEmpty()) {
            eventSql.append(" WHERE title LIKE ?");
        }
        
        eventSql.append(" LIMIT ?, ?");
        
        if (eventName != null && !eventName.isEmpty()) {
            return jdbcTemplate.query(eventSql.toString(), new Object[]{"%" + eventName + "%", offset, eventsPerPage}, new BeanPropertyRowMapper<>(EventDAO.class));
        } else {
            return jdbcTemplate.query(eventSql.toString(), new Object[]{offset, eventsPerPage}, new BeanPropertyRowMapper<>(EventDAO.class));
        }
    }

    @Override
    public int getTotalFilteredEvents(String eventName) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM event");
        
        // Check if filtering by eventName
        if (eventName != null && !eventName.isEmpty()) {
            countSql.append(" WHERE title LIKE ?");
            return jdbcTemplate.queryForObject(countSql.toString(), new Object[]{"%" + eventName + "%"}, Integer.class);
        } else {
            return jdbcTemplate.queryForObject(countSql.toString(), Integer.class);
        }
    }

    @Override
    public List<EventDAO> filterEvents(int year, String[] selectedMonths, int page, int eventsPerPage) {
        int offset = (page - 1) * eventsPerPage;

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

        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(EventDAO.class));
    }

    @Override
    public int getTotalFilteredEvents(int year, String[] selectedMonths) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM event WHERE YEAR(start_date) = ? AND MONTH(start_date) IN ("
                + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")");
        
        List<Object> countParamsList = new ArrayList<>();
        countParamsList.add(year);

        for (String selectedMonth : selectedMonths) {
            countParamsList.add(getMonthNumericValue(selectedMonth));
        }

        Object[] countParams = countParamsList.toArray();

        return jdbcTemplate.queryForObject(countSql.toString(), countParams, Integer.class);
    }

    private int getMonthNumericValue(String month) {
        switch (month.toLowerCase()) {
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

    @Override
    public EventDAO getEventById(int eventId) {
        String sql = "SELECT id, title, start_date, end_date, location, organizer, description, image_data FROM event WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{eventId}, new BeanPropertyRowMapper<>(EventDAO.class));
    }
    
@Override
    public UserDAO getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserDAO.class));
    }
}
