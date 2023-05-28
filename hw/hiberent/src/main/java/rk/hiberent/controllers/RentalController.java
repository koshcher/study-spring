package rk.hiberent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rk.hiberent.dtos.RentalSaving;
import rk.hiberent.models.Apartment;
import rk.hiberent.models.Rental;
import rk.hiberent.repositories.ApartmentRepository;
import rk.hiberent.repositories.ClientRepository;
import rk.hiberent.repositories.RentalRepository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;

@Controller
public class RentalController {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/rentals")
    public String rentals(Model model,
                          @RequestParam(required = false) String filter
    ) {
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
        return "rental/list";
    }

    @PostMapping("/rentals/save")
    public String save(RentalSaving rentalSaving) {
        var client = clientRepository.getClientById(rentalSaving.getClientId());
        if(client.isEmpty())  return "redirect:/rentals";

        var apartment = apartmentRepository.getApartmentById(rentalSaving.getApartmentId());
        if(apartment.isEmpty())  return "redirect:/rentals";

        var rental = new Rental();
        if(rentalSaving.getId() != null) rental.setId(rentalSaving.getId());
        rental.setClient(client.get());
        rental.setApartment(apartment.get());
        rental.setEndDate(rentalSaving.getEndDate());
        rental.setStartDate(rentalSaving.getStartDate());

        rentalRepository.save(rental);
        return "redirect:/rentals";
    }

    @GetMapping("/rentals/delete/{id}")
    public String delete(@PathVariable Long id) {
        rentalRepository.deleteById(id);
        return "redirect:/rentals";
    }

    @GetMapping("/rentals/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        var rental = rentalRepository.getRentalById(id);
        if(rental.isEmpty()) return "redirect:/rentals";
        model.addAttribute("rental", rental.get());
        return "rental/edit";
    }
}
