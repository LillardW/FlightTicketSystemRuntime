package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DynamicQueryDao {

    @PersistenceContext(unitName = "springJpa")
    EntityManager em;

    public List<Flight> findFlightsByDepartureCityOrArrivalCityOrEstimatedTakeOffTime(String departureCity, String arrivalCity, Date takeOffTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> query = cb.createQuery(Flight.class);

        Root<Flight> root = query.from(Flight.class);
        query.select(root);

        Predicate p1 = null;
        if(departureCity!=null) {
            Predicate p2 = cb.equal(root.get(Flight.departureCity),departureCity);
            if(p1 != null) {
                p1 = cb.and(p1,p2);
            } else {
                p1 = p2;
            }
        }

        if(arrivalCity!=null) {
            Predicate p3 = cb.equal(root.get(Flight.arrivalCity),arrivalCity);
            if(p1 != null) {
                p1 = cb.and(p1,p3);
            } else {
                p1 = p3;
            }
        }

        if(takeOffTime!=null) {
            Predicate p4 = cb.greaterThanOrEqualTo(root.get(Flight.estimatedTakeOffTime),takeOffTime);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(takeOffTime);
            calendar.add(Calendar.DATE,1);
            Predicate p5 = cb.lessThan(root.get(Flight.estimatedTakeOffTime),calendar.getTime());
            if(p1 != null) {
                p1 = cb.and(p1,p4,p5);
            } else {
                p1 = p4;
                p1 = cb.and(p1,p5);
            }
        }

        query.where(p1);
        List<Flight> result = em.createQuery(query).getResultList();
        return result;
    }
}
