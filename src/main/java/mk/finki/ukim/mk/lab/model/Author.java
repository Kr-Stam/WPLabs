package mk.finki.ukim.mk.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Author {
    private Long id;
    private String name;
    private String surname;
    private String biography;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(surname);
        return sb.toString();
    }
}
