package abdkzmv.wm2.assignment2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 50, message = "Title must be between {min} and {max} characters")
    private String title;

    @NotBlank(message = "Director cannot be blank")
    @Size(min = 2, max = 50, message = "Director name must be between {min} and {max} characters")
    private String director;


    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
    }
}