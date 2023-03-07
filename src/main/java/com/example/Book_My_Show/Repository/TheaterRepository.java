package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {

    @Query(value = "select location, name from theaters ", nativeQuery = true)
    List<String> getAllTheaters();
    @Query(value = "select theaters.name, theaters.location, shows.show_date, shows.show_time from theaters join shows where theaters.id=shows.theater_entity_id and theaters.name=:name ", nativeQuery = true)
    List<String> shows(String name);

}
