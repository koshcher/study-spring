package rk.hiberent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rk.hiberent.models.Landlord;
import rk.hiberent.repositories.LandlordRepository;

@Controller
public class LandlordController {
    @Autowired
    private LandlordRepository landlordRepository;

    @GetMapping("/landlords")
    public String landlords(Model model) {
        var landlords = landlordRepository.findAll();
        model.addAttribute("landlords", landlords);
        return "landlord/list";
    }

    @PostMapping("/landlords/save")
    public String save(Landlord landlord) {
        landlordRepository.save(landlord);
        return "redirect:/landlords";
    }

    @GetMapping("/landlords/delete/{id}")
    public String delete(@PathVariable Long id) {
        landlordRepository.deleteById(id);
        return "redirect:/landlords";
    }


    @GetMapping("/landlords/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        var landlord = landlordRepository.getLandlordById(id);
        if(landlord.isEmpty()) return "redirect:/landlords";
        model.addAttribute("landlord", landlord.get());
        return "landlord/edit";
    }
}
