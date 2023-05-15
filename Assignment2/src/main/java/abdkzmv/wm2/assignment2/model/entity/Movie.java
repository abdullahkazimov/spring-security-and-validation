package abdkzmv.wm2.assignment2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "title cannot be empty")
    private String title;

    @NotBlank(message = "director name is empty")
    @Size(min=3,max = 100,message = "name size must be between {min} and {max}")
    private String director;


    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
    }
}