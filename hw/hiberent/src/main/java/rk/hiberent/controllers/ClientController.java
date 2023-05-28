package rk.hiberent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rk.hiberent.models.Client;
import rk.hiberent.repositories.ClientRepository;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String clients(Model model,
                          @RequestParam(required = false) String name,
                          @RequestParam(required = false) String phone,
                          @RequestParam(required = false) String district,
                          @RequestParam(required = false) Integer price,
                          @RequestParam(required = false) Integer roomCount
                          ) {
        if(name == null) name = "";
        if(phone == null) phone = "";
        if(district == null) district = "";
        if(price == null) price = Integer.MAX_VALUE;

        List<Client> clients;

        if(roomCount == null) {
            clients = clientRepository.findAllByNameContainingIgnoreCaseAndPhoneStartingWithAndPreferDistrictContainingIgnoreCaseAndPreferPriceLessThanEqual(name, phone, district, price);
        } else  {
            clients = clientRepository.findAllByNameContainingIgnoreCaseAndPhoneStartingWithAndPreferDistrictContainingIgnoreCaseAndPreferPriceLessThanEqualAndPreferRoomCountEquals(name, phone, district, price, roomCount);
        }

        model.addAttribute("clients", clients);
        return "clients";
    }

    @PostMapping("/clients/new")
    public String newClient(Client client) {
        clientRepository.save(client);
        return "redirect:/";
    }

    @GetMapping("/clients/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/clients/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        var client = clientRepository.getClientById(id);
        if(client.isEmpty()) return "redirect:/clients";
        model.addAttribute("client", client.get());
        return "edit_client";
    }

    @PostMapping("/clients/edit")
    public String editPost(Client client) {
        clientRepository.save(client);
        return "redirect:/";
    }

}
