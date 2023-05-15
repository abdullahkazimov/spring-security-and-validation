package abdkzmv.wm2.assignment2.init;

import abdkzmv.wm2.assignment2.model.entity.Movie;
import abdkzmv.wm2.assignment2.model.entity.User;
import abdkzmv.wm2.assignment2.repository.UserRepository;
import abdkzmv.wm2.assignment2.service.inter.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataBootstrapper {
    @Bean
    public ApplicationRunner addUsers(UserRepository userRepo,
                                      PasswordEncoder encoder) {
        return (args) -> {

            userRepo.save(new User("test", encoder.encode("test"),
                    "test@gmail.com"));
            User adminUser = new User("admin", encoder.encode("admin"),
                    "admin@gmail.com");

            userRepo.save(adminUser.addRole("ROLE_ADMIN"));

            userRepo.save(new User("user", encoder.encode("user"),
                    "user@gmail.com"));
        };
    }

    @Bean
    @Autowired
    public CommandLineRunner addMovies(MovieService movieService) {
        // adding movie data
        return (args) -> {
            movieService.addMovie(new Movie("The Shawshank Redemption", "Frank Darabont"));
            movieService.addMovie(new Movie("The Godfather", "Francis Ford Coppola"));
            movieService.addMovie(new Movie("The Dark Knight", "Christopher Nolan"));
            movieService.addMovie(new Movie("Forrest Gump", "Robert Zemeckis"));
            movieService.addMovie(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson"));
            movieService.addMovie(new Movie("Inception", "Christopher Nolan"));
            movieService.addMovie(new Movie("Pulp Fiction", "Quentin Tarantino"));
            movieService.addMovie(new Movie("Fight Club", "David Fincher"));
            movieService.addMovie(new Movie("The Matrix", "Lana Wachowski, Lilly Wachowski"));
            movieService.addMovie(new Movie("Star Wars: Episode IV - A New Hope", "George Lucas"));
        };
    }



}
