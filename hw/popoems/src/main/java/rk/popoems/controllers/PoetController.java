package rk.popoems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rk.popoems.services.PoetService;

import java.util.List;

@RestController
@RequestMapping("/poets")
public class PoetController {

    @Autowired
    private PoetService poetService;


    @GetMapping("/{poetId}/bio")
    public String getPoetBio(@PathVariable Long poetId) {
        return poetService.getPoetBio(poetId);
    }

    @GetMapping("/poem/{poemId}")
    public String getPoetPoem(@PathVariable Long poemId) {
        return poetService.getPoem(poemId);
    }

    @GetMapping("/{poetId}/poems")
    public List<String> getPoetPoems(@PathVariable Long poetId) {
        return poetService.getPoetPoems(poetId);
    }

    @GetMapping("/{poetId}/random-poem")
    public String getRandomPoem(@PathVariable Long poetId) {
        return poetService.getRandomPoem(poetId);
    }
}

