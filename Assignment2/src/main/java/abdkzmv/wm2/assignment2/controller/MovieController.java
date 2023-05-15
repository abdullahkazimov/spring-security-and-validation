package abdkzmv.wm2.assignment2.controller;

import abdkzmv.wm2.assignment2.model.entity.Movie;
import abdkzmv.wm2.assignment2.service.inter.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user/")
public class MovieController {
    static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping({"/","/movies","/movies/"})
    public String getMovies(Model model){
        List<Movie> movies = movieService.list();
        LOGGER.info("getMovies()");

        model.addAttribute("movies", movies);
        return "movies/list";
    }

    @GetMapping("movies/new")
    public String createNewMovie(Model model) {
        model.addAttribute("movie", new Movie());
        LOGGER.info("createNewMovie()");
        return "movies/new";
    }

    @PostMapping("movies/addMovie")
    public String save(@ModelAttribute("movie") Movie Movie) {
        movieService.addMovie(Movie);
        LOGGER.info("save()");
        return "redirect:/user/movies";
    }

    @GetMapping("movies/update/{id}")
    public String updateMovie(@PathVariable Long id, Model model) {
        model.addAttribute("updatedMovie", movieService.findById(id));
        LOGGER.info("updateMovie()");
        return "movies/update";
    }

    @PostMapping("/movies/updateMovie")
    public String postMovie(Model model, @ModelAttribute("updatedMovie") Movie Movie, Long id) {
        movieService.update(Movie);
        LOGGER.info("postMovie()");
        return "redirect:/user/movies/";
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteById(id);
        LOGGER.info("deleteMovie()");
        return "redirect:/user/movies/";
    }
}