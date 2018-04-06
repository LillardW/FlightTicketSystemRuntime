package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
