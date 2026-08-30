package com.soundhar.travelportal.controller;

import com.soundhar.travelportal.model.Booking;
import com.soundhar.travelportal.model.TravelPackage;
import com.soundhar.travelportal.model.User;
import com.soundhar.travelportal.repository.BookingRepository;
import com.soundhar.travelportal.repository.PackageRepository;
import com.soundhar.travelportal.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private PackageRepository packageRepository;
    @Autowired private UserRepository userRepository;

    // Show the booking form for a chosen package
    @GetMapping("/new/{packageId}")
    public String showBookingForm(@PathVariable Long packageId, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUserId") == null) {
            return "redirect:/login";
        }
        TravelPackage travelPackage = packageRepository.findById(packageId).orElseThrow();
        model.addAttribute("travelPackage", travelPackage);
        return "booking-form";
    }

    // Create the booking (status = PENDING until "payment" confirms it)
    @PostMapping("/new/{packageId}")
    public String createBooking(@PathVariable Long packageId,
                                 @RequestParam LocalDate travelDate,
                                 @RequestParam Integer numberOfTravelers,
                                 HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userRepository.findById(userId).orElseThrow();
        TravelPackage travelPackage = packageRepository.findById(packageId).orElseThrow();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);
        booking.setTravelDate(travelDate);
        booking.setNumberOfTravelers(numberOfTravelers);
        booking.setTotalAmount(travelPackage.getPrice().multiply(BigDecimal.valueOf(numberOfTravelers)));
        booking.setStatus("PENDING");

        Booking saved = bookingRepository.save(booking);
        return "redirect:/booking/payment/" + saved.getId();
    }

    // Simulated payment page
    @GetMapping("/payment/{bookingId}")
    public String showPaymentPage(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        model.addAttribute("booking", booking);
        return "payment";
    }

    // Confirm the simulated payment
    @PostMapping("/payment/{bookingId}")
    public String confirmPayment(@PathVariable Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        // In a real app this is where you'd integrate a payment gateway (Razorpay/Stripe)
        // and only mark PAID after the gateway confirms the transaction.
        booking.setStatus("PAID");
        bookingRepository.save(booking);
        return "redirect:/booking/confirmation/" + booking.getId();
    }

    @GetMapping("/confirmation/{bookingId}")
    public String confirmation(@PathVariable Long bookingId, Model model) {
        model.addAttribute("booking", bookingRepository.findById(bookingId).orElseThrow());
        return "confirmation";
    }

    // My bookings
    @GetMapping("/my")
    public String myBookings(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        if (userId == null) {
            return "redirect:/login";
        }
        User user = userRepository.findById(userId).orElseThrow();
        List<Booking> bookings = bookingRepository.findByUser(user);
        model.addAttribute("bookings", bookings);
        return "my-bookings";
    }
}
