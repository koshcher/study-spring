package rk.hiberent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rk.hiberent.models.Rental;
import rk.hiberent.repositories.ApartmentRepository;
import rk.hiberent.repositories.LandlordRepository;
import rk.hiberent.repositories.RentalRepository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private RentalRepository rentalRepository;


    @GetMapping("/landlords")
    public String landlords(Model model) {
        var landlords = landlordRepository.findAll();
        model.addAttribute("landlords", landlords);
        return "landlords";
    }
    @GetMapping("/apartments")
    public String apartments(Model model) {
        var apartments = apartmentRepository.findAll();
        model.addAttribute("apartments", apartments);
        return "apartments";
    }

    @GetMapping("/rentals")
    public String rentals(Model model,
                          @RequestParam(required = false) String filter) {

        List<Rental> rentals;
        if(filter == null) {
          rentals = rentalRepository.findAll();
        } else if(Objects.equals(filter, "start-this-month")) {
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = currentDate.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endDate = currentDate.with(TemporalAdjusters.lastDayOfMonth());
            rentals = rentalRepository.findAllByStartDateBetween(startDate, endDate);
        } else if (Objects.equals(filter, "end-this-month")) {
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = currentDate.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endDate = currentDate.with(TemporalAdjusters.lastDayOfMonth());
            rentals = rentalRepository.findAllByEndDateBetween(startDate, endDate);
        } else if (Objects.equals(filter, "avg-less-month")) {
            rentals = rentalRepository.findAllAverageRentalLessThanMonth();
        } else if(Objects.equals(filter, "avg-more-year")){
            rentals = rentalRepository.findAllAverageRentalMoreThanYear();
        }
        else {
            rentals = rentalRepository.findAll();
        }

        model.addAttribute("rentals", rentals);
        return "rentals";
    }
}
