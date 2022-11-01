package com.example.PathFinder.services.route;

import com.example.PathFinder.models.Route;
import com.example.PathFinder.viewModels.route.RouteDetailsView;
import com.example.PathFinder.viewModels.route.RouteIndexView;

import java.util.List;

public interface IRouteService {
	List<Route> getMostCommented();

	List<RouteIndexView> getAllRoutes();

	RouteDetailsView getRouteById(Long id);
}
