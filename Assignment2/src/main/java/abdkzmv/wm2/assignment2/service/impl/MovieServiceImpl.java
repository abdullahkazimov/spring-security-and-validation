package abdkzmv.wm2.assignment2.service.impl;

import abdkzmv.wm2.assignment2.model.entity.Movie;
import abdkzmv.wm2.assignment2.repository.MovieRepository;
import abdkzmv.wm2.assignment2.service.inter.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepo;

    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Movie> list() {
        return movieRepo.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepo.findById(id).orElse(null);
    }

    @Override
    public void addMovie(Movie newMovie) {
        movieRepo.save(newMovie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepo.deleteById(id);
    }

    @Override
    public void update(Movie updatedMovie) {
        deleteById(updatedMovie.getId());
        addMovie(updatedMovie);
    }
}
