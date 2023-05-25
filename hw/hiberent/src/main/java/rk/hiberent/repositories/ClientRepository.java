package rk.hiberent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rk.hiberent.models.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> getClientsById(Long aLong);

    List<Client> findAllByNameContainingIgnoreCaseAndPhoneStartingWithAndPreferDistrictContainingIgnoreCaseAndPreferPriceLessThanEqual(String name, String phone, String preferDistrict, int preferPrice);

    List<Client> findAllByNameContainingIgnoreCaseAndPhoneStartingWithAndPreferDistrictContainingIgnoreCaseAndPreferPriceLessThanEqualAndPreferRoomCountEquals(String name, String phone, String preferDistrict, int preferPrice, int preferRoomCount);
}
