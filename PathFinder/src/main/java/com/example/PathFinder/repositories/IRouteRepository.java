package com.example.PathFinder.repositories;

import com.example.PathFinder.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRouteRepository extends JpaRepository<Route, Long> {
	@Query("SELECT r FROM Route AS r ORDER BY size(r.comments) DESC")
	List<Route> findMostCommented();
}
