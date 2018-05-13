package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.*;
import com.flightticketsystem.runtime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class DynamicQueryDao {

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager em;

    public List<Flight> findFlightsByDepartureCityOrArrivalCityOrEstimatedTakeOffTime(String departureCity, String arrivalCity, Date takeOffTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> query = cb.createQuery(Flight.class);

        Root<Flight> root = query.from(Flight.class);
        query.select(root);

        Predicate p1 = null;
        if (departureCity != null && !departureCity.equals("")) {
            Predicate p2 = cb.equal(root.get("departureCity"), departureCity);
            if (p1 != null) {
                p1 = cb.and(p1, p2);
            } else {
                p1 = p2;
            }
        }

        if (arrivalCity != null && !arrivalCity.equals("")) {
            Predicate p3 = cb.equal(root.get("arrivalCity"), arrivalCity);
            if (p1 != null) {
                p1 = cb.and(p1, p3);
            } else {
                p1 = p3;
            }
        }

        if (takeOffTime != null) {
            Predicate p4 = cb.greaterThanOrEqualTo(root.get("estimatedTakeOffTime"), takeOffTime);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(takeOffTime);
            calendar.add(Calendar.DATE, 1);
            Predicate p5 = cb.lessThan(root.get("estimatedTakeOffTime"), calendar.getTime());
            if (p1 != null) {
                p1 = cb.and(p1, p4, p5);
            } else {
                p1 = p4;
                p1 = cb.and(p1, p5);
            }
        }

        query.where(p1);
        return em.createQuery(query).getResultList();
    }

    public List<Map<String, Object>> searchSeatCharts(int flightId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Place> query = cb.createQuery(Place.class);

        Root<Place> root = query.from(Place.class);
        query.select(root);

        Predicate p1 = null;
        if (flightId != 0) {
            Predicate p2 = cb.equal(root.get("flightId"), flightId);
            if (p1 != null) {
                p1 = cb.and(p1, p2);
            } else {
                p1 = p2;
            }
        }

        query.where(p1);
        List<Place> resultList = em.createQuery(query).getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for(Place place:resultList) {
            result.add(convertToMap(place));
        }
        return result;
    }

    public List<UserModel> searchAllUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);

        Root<User> root = query.from(User.class);
        query.select(root);

        Predicate p1 = cb.notEqual(root.get("authority"),1);
        query.where(p1);
        List<User> resultList = em.createQuery(query).getResultList();
        List<UserModel> returnList = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for(User user:resultList) {
            returnList.add(userService.convertToModel(user));
        }
        return returnList;
    }

    public List<Ticket> searchTickets(TicketSearchModel ticketSearchModel) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = cb.createQuery(Ticket.class);

        Root<Ticket> root = query.from(Ticket.class);
        query.select(root);

        Predicate p1 = cb.equal(root.get("userId"),ticketSearchModel.getUserId());
        query.where(p1);

        Predicate p2 = cb.equal(root.get(""),ticketSearchModel.getFlightNo());
        query.where(p2);

        List<Ticket> resultList = em.createQuery(query).getResultList();
        return resultList;
    }

    public Map<String, Object> convertToMap(Place place) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("placeId",place.getPlaceId());
        map.put("flightId",place.getFlightId());
        map.put("placeNo", place.getPlaceNo());
        map.put("placeStatus", place.getPlaceStatus());
        map.put("placeLevel", place.getPlaceLevel());
        return map;
    }
}
