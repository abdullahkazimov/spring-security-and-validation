package abdkzmv.wm2.assignment2.model.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class Role {

    private static Long ID = 0L;
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
        this.id = ++ID;
    }

    @Override
    public String toString() {
        return name;
    }
}
