package mk.finki.ukim.mk.lab.model;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AuthorFullName implements Serializable {

    private String name;
    private String surname;

    @Override
    public String toString() {
        return "AuthorFullName{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
