package abdkzmv.wm2.assignment2.model.dto;

import abdkzmv.wm2.assignment2.model.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String roles;
    private List<Role> rolesList;
}
