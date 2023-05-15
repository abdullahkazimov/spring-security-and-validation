package abdkzmv.wm2.assignment2.repository;

import abdkzmv.wm2.assignment2.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
