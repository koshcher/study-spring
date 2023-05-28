package rk.hiberent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rk.hiberent.dtos.ApartmentSaving;
import rk.hiberent.models.Apartment;
import rk.hiberent.repositories.ApartmentRepository;
import rk.hiberent.repositories.LandlordRepository;

@Controller
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private LandlordRepository landlordRepository;

    @GetMapping("/apartments")
    public String apartments(Model model) {
        var apartments = apartmentRepository.findAll();

        int max = 0;
        int min = 0;
        long avg = 0;

        if(apartments.size() > 0) {
            var firstApartmentPrice = apartments.get(0).getPrice();
            max = firstApartmentPrice;
            min = firstApartmentPrice;

            long sum = 0;
            for (var apartment: apartments) {
                int price = apartment.getPrice();
                sum += price;
                if(price > max) max = price;
                if(price < min) min = price;
            }
            avg = sum / apartments.size();
        }

        model.addAttribute("apartments", apartments);
        model.addAttribute("avgPrice", avg);
        model.addAttribute("minPrice", min);
        model.addAttribute("maxPrice", max);

        return "apartment/list";
    }

    @PostMapping("/apartments/save")
    public String save(ApartmentSaving apartmentSaving) {
        var landlord = landlordRepository.getLandlordById(apartmentSaving.getLandlordId());
        if(landlord.isEmpty())  return "redirect:/apartments";

        var apartment = new Apartment();
        if(apartmentSaving.getId() != null) apartment.setId(apartmentSaving.getId());
        apartment.setLandlord(landlord.get());
        apartment.setDistrict(apartmentSaving.getDistrict());
        apartment.setPrice(apartmentSaving.getPrice());
        apartment.setRoomCount(apartmentSaving.getRoomCount());

        apartmentRepository.save(apartment);
        return "redirect:/apartments";
    }

    @GetMapping("/apartments/delete/{id}")
    public String delete(@PathVariable Long id) {
        apartmentRepository.deleteById(id);
        return "redirect:/apartments";
    }

    @GetMapping("/apartments/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        var apartment = apartmentRepository.getApartmentById(id);
        if(apartment.isEmpty()) return "redirect:/apartments";
        model.addAttribute("apartment", apartment.get());
        return "apartment/edit";
    }

}
