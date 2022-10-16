package com.example.PathFinder.web;

import com.example.PathFinder.services.route.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private final IRouteService routeService;

	@Autowired
	public HomeController(IRouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping("/")
	public String home(Model model) {
		var routes = this.routeService.getMostCommented();

		model.addAttribute("mostCommented", routes.get(0));

		return "index";
	}
}
