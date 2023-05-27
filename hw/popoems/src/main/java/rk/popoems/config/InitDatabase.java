package rk.popoems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rk.popoems.models.Category;
import rk.popoems.models.Poem;
import rk.popoems.models.Poet;
import rk.popoems.repositories.PoemRepository;
import rk.popoems.repositories.PoetRepository;

import java.util.Arrays;

@Configuration
public class InitDatabase {

    @Autowired
    private PoemRepository poemRepository;
    @Autowired
    private PoetRepository poetRepository;

    @Bean
    public CommandLineRunner initPoets(PoetRepository poetRepository) {
        return args -> {
            poetRepository.save(new Poet("Moro", "Boro", "bio fio",
                    Arrays.asList(
                            new Poem("Naga i ruka", "textura", Category.LYRIC),
                            new Poem("Poem boem", "popoem tobi pisnu", Category.ODE)
                    )));
            poetRepository.save(new Poet("Jaran", "Baran", "ara bio",
                    Arrays.asList(
                            new Poem("Horom", "Jnorski prinesi vodi", Category.EPIC),
                            new Poem("Wot naglec", "babka", Category.FABLE)
                    )));
        };
    }
}
