package com.example.PathFinder.services.route;

import com.example.PathFinder.exceptions.RouteNotFoundException;
import com.example.PathFinder.models.Route;
import com.example.PathFinder.repositories.IRouteRepository;
import com.example.PathFinder.viewModels.route.RouteDetailsView;
import com.example.PathFinder.viewModels.route.RouteIndexView;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RouteService implements IRouteService {
    private final IRouteRepository routeRepository;

    public RouteService(IRouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getMostCommented() {
        return this.routeRepository.findMostCommented();
    }

    public List<RouteIndexView> getAllRoutes() {
        return this.routeRepository.findAll()
                .stream()
                .map(route -> new RouteIndexView(
                        route.getId(),
                        route.getName(),
                        route.getDescription(),
                        route.getPictures().stream().findFirst().get().getUrl()
                ))
                .collect(toList());
    }

    public RouteDetailsView getRouteById(Long id) {
        return this.routeRepository.findById(id)
                .map(route -> new RouteDetailsView(
                        route.getId(),
                        route.getGpxCoordinates(),
                        route.getLevel().name(),
                        route.getName(),
                        route.getDescription(),
                        route.getAuthor().getFullName(),
                        route.getVideoUrl(),
                        route.getPictures()
                                .stream()
                                .map(picture -> picture.getUrl()).collect(toList())
                )).orElseThrow(RouteNotFoundException::new);
    }
}
