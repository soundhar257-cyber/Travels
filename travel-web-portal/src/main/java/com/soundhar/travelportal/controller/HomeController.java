package com.soundhar.travelportal.controller;

import com.soundhar.travelportal.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Show a few featured packages on the landing page
        model.addAttribute("packages", packageRepository.findAll());
        return "index";
    }
}
