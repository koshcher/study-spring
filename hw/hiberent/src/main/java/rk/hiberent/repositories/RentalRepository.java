package rk.hiberent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rk.hiberent.models.Rental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> getRentalById(Long id);

    List<Rental> findAllByEndDateBetween(LocalDate endDate, LocalDate endDate2);

    List<Rental> findAllByStartDateBetween(LocalDate startDate, LocalDate startDate2);

    @Query("select r from Rental as r " +
            "group by r.id " +
            "having avg(TIMESTAMPDIFF(day, r.endDate, r.startDate)) < 30")
    List<Rental> findAllAverageRentalLessThanMonth();

    @Query("select r from Rental as r " +
            "group by r.client " +
            "having avg(TIMESTAMPDIFF(DAY, r.endDate, r.startDate)) > 364")
    List<Rental> findAllAverageRentalMoreThanYear();
}
