package rk.popoems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rk.popoems.models.Poet;

@Repository
public interface PoetRepository extends JpaRepository<Poet, Long> {

}
