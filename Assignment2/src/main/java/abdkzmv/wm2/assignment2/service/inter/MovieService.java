package abdkzmv.wm2.assignment2.service.inter;

import abdkzmv.wm2.assignment2.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> list();
    Movie findById(Long id);
    void addMovie(Movie newMovie);
    void deleteById(Long id);
    void update(Movie updatedMovie);
}
