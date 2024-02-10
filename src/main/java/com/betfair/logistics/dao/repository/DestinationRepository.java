package com.betfair.logistics.dao.repository;

import com.betfair.logistics.dao.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {

    <Optional> Destination findByName(String name);
}
