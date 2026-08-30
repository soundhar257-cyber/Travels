package com.soundhar.travelportal.controller;

import com.soundhar.travelportal.model.TravelPackage;
import com.soundhar.travelportal.repository.PackageRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping
    public String listPackages(@RequestParam(required = false) String destination, Model model, HttpSession session) {
        List<TravelPackage> packages;

        if (destination != null && !destination.isBlank()) {
            packages = packageRepository.findByDestinationContainingIgnoreCase(destination);
        } else {
            packages = packageRepository.findAll();
        }

        model.addAttribute("packages", packages);
        model.addAttribute("searchTerm", destination);
        model.addAttribute("isLoggedIn", session.getAttribute("loggedInUserId") != null);
        return "packages";
    }

    @GetMapping("/{id}")
    public String packageDetail(@PathVariable Long id, Model model) {
        TravelPackage travelPackage = packageRepository.findById(id).orElseThrow();
        model.addAttribute("travelPackage", travelPackage);
        return "package-detail";
    }
}
