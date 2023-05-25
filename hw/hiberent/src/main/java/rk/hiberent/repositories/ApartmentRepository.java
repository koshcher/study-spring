package rk.hiberent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rk.hiberent.models.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
