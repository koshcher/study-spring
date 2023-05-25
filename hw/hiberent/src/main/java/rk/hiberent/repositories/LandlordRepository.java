package rk.hiberent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rk.hiberent.models.Landlord;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long> {
}
