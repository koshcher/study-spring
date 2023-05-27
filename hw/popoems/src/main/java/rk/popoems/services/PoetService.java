package rk.popoems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rk.popoems.models.Poem;
import rk.popoems.models.Poet;
import rk.popoems.repositories.PoemRepository;
import rk.popoems.repositories.PoetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PoetService {
    @Autowired
    private PoetRepository poetRepository;
    private PoemRepository poemRepository;

    public String getPoetBio(Long poetId) {
        Optional<Poet> poet = poetRepository.findById(poetId);
        return poet.get().getBio();
    }

    public String getPoem(Long poemId) {
        Optional<Poem> poem = poemRepository.findById(poemId);
        return poem.get().getText();
    }

    public List<String> getPoetPoems(Long poetId) {
        List<String> res = new ArrayList<>();
        Optional<Poet> poet = poetRepository.findById(poetId);
        List<Poem> poems = poet.get().getPoems();

        for (Poem poem : poems) {
            res.add(poem.getName());
        }
        return res;
    }

    public String getRandomPoem(Long poetId) {
        Optional<Poet> poet = poetRepository.findById(poetId);
        List<Poem> poems = poet.get().getPoems();

        Random rand = new Random();
        Poem poem = poems.get(rand.nextInt(poems.size()));
        return poem.getText();
    }
}
