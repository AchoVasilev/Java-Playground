package com.example.PathFinder.services.route;

import com.example.PathFinder.models.Route;
import com.example.PathFinder.repositories.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements IRouteService {
	private final IRouteRepository routeRepository;

	@Autowired
	public RouteService(IRouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	@Override
	public List<Route> getMostCommented() {
		return this.routeRepository.findMostCommented();
	}
}
