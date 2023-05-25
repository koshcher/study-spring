package rk.hiberent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rk.hiberent.models.Apartment;
import rk.hiberent.models.Client;
import rk.hiberent.models.Landlord;
import rk.hiberent.models.Rental;
import rk.hiberent.repositories.ApartmentRepository;
import rk.hiberent.repositories.ClientRepository;
import rk.hiberent.repositories.LandlordRepository;
import rk.hiberent.repositories.RentalRepository;

import java.time.LocalDate;
import java.util.Date;

@Configuration
public class InitDatabase {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private RentalRepository rentalRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Client roma = new Client("roma", "roma", "+380999999999", 1500, 2, "Some district");
            Client vitaliy = new Client("vitaliy", "vitaliy", "+380777777777", 500, 1, "Another district");

            clientRepository.save(roma);
            clientRepository.save(vitaliy);

            Landlord king = new Landlord("king", "king", "+380888888888");
            landlordRepository.save(king);

            Apartment free = new Apartment(1245, "Some district", 3, king);
            Apartment taken = new Apartment(856, "Some district", 1, king);
            apartmentRepository.save(free);
            apartmentRepository.save(taken);

            Rental rental = new Rental(taken, roma, LocalDate.now(), LocalDate.now().plusDays(31));
            rentalRepository.save(rental);
        };
    }

}
