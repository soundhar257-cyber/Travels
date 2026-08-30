package com.soundhar.travelportal.controller;

import com.soundhar.travelportal.model.TravelPackage;
import com.soundhar.travelportal.repository.BookingRepository;
import com.soundhar.travelportal.repository.PackageRepository;
import com.soundhar.travelportal.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private PackageRepository packageRepository;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private UserRepository userRepository;

    // Simple guard: only ROLE_ADMIN may access anything under /admin
    private boolean isNotAdmin(HttpSession session) {
        return !"ROLE_ADMIN".equals(session.getAttribute("role"));
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        model.addAttribute("totalPackages", packageRepository.count());
        model.addAttribute("totalBookings", bookingRepository.count());
        model.addAttribute("totalUsers", userRepository.count());
        return "admin/dashboard";
    }

    // --- Manage packages ---

    @GetMapping("/packages")
    public String managePackages(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        model.addAttribute("packages", packageRepository.findAll());
        return "admin/packages";
    }

    @GetMapping("/packages/new")
    public String newPackageForm(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        model.addAttribute("travelPackage", new TravelPackage());
        return "admin/package-form";
    }

    @PostMapping("/packages/save")
    public String savePackage(@ModelAttribute TravelPackage travelPackage, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        packageRepository.save(travelPackage);
        return "redirect:/admin/packages";
    }

    @GetMapping("/packages/delete/{id}")
    public String deletePackage(@PathVariable Long id, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        packageRepository.deleteById(id);
        return "redirect:/admin/packages";
    }

    // --- Manage bookings ---

    @GetMapping("/bookings")
    public String manageBookings(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin/bookings";
    }

    // --- Manage users ---

    @GetMapping("/users")
    public String manageUsers(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login";
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
}
