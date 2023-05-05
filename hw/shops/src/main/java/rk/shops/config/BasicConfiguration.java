package rk.shops.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rk.shops.models.Shop;
import rk.shops.repositories.ShopRepository;

@Configuration
public class BasicConfiguration {

    @Autowired
    private ShopRepository shopRepository;

    @Bean
    public CommandLineRunner initDatabase(ApplicationContext ctx) {
        return args -> {
            Shop testShop = Shop.builder()
                    .name("Crazy pizza")
                    .address("Crazy street, Ukraine")
                    .link("https://crazypizza.com")
                    .phone("+38066875032385")
                    .email("support@crazypizza.com")
                    .category("food")
                    .description("Super crazy pizza shop where you will lost everything")
                    .build();

            shopRepository.save(testShop);
        };
    }
}
