package rk.hiber1;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rk.hiber1.db.Town;
import rk.hiber1.db.TownRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class TownController {

    @Autowired
    private TownRepository townRepository;

    @GetMapping("/")
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Long residentsCount,
                       @RequestParam(required = false) String history
    ) {
        if(name == null) name = "";
        if(history == null) history = "";

        List<Town> towns;
        if(residentsCount == null) {
            towns = townRepository.getAllByNameContainingIgnoreCaseAndHistoryContainingIgnoreCase(name, history);
        } else  {
            towns = townRepository.getAllByNameContainingIgnoreCaseAndHistoryContainingIgnoreCaseAndResidentsCountEquals(name, history, residentsCount);
        }
        model.addAttribute("towns", towns);
        return "list";
    }

    @PostMapping("/add")
    public String add(Town town) {
        townRepository.save(town);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        townRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        Optional<Town> town = townRepository.getTownById(id);
        if(town.isEmpty()) return "redirect:/";

        model.addAttribute("town", town.get());
        return "edit";
    }

    @PostMapping("/edit")
    public String postEdit(Town town) {
        townRepository.save(town);
        return "redirect:/";
    }
}
