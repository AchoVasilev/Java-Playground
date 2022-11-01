package com.example.PathFinder.web;

import com.example.PathFinder.services.route.IRouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/routes")
public class RoutesController {
    private final IRouteService routeService;

    public RoutesController(IRouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String getAllRoutes(Model model) {
        var routes = this.routeService.getAllRoutes();

        model.addAttribute("routes", routes);

        return "routes";
    }

    @GetMapping("/details/{id}")
    public String getRoute(@PathVariable("id") Long routeId, Model model) {
        var route = this.routeService.getRouteById(routeId);

        model.addAttribute("route", route);

        return "route-details";
    }
}
