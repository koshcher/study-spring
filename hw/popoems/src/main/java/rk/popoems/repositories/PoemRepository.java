package rk.popoems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rk.popoems.models.Poem;

@Repository
public interface PoemRepository extends JpaRepository<Poem, Long> {

}
