package rk.hiber1.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Long> {
    Optional<Town> getTownById(Long id);

    List<Town> getAllByNameContainingIgnoreCaseAndHistoryContainingIgnoreCase(String name, String history);
    List<Town> getAllByNameContainingIgnoreCaseAndHistoryContainingIgnoreCaseAndResidentsCountEquals(String name, String history, long residentsCount);
}
