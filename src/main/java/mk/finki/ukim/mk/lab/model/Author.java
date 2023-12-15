package mk.finki.ukim.mk.lab.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = AuthorFullNameConverter.class)
    private AuthorFullName authorFullName;
    private String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST)
    @Nullable
    List<Book> books;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(authorFullName);
        return sb.toString();
    }

    public Author(Long id, String name, String surname, String biography, LocalDate dateOfBirth, List<Book> books) {
        this.id = id;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
        this.authorFullName = new AuthorFullName(name, surname);
    }
}

class AuthorFullNameConverter implements AttributeConverter<AuthorFullName, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(AuthorFullName authorFullName) {
       if(authorFullName == null){
           return null;
       }
       StringBuilder sb = new StringBuilder();
       if(authorFullName.getName() != null && !authorFullName.getName().isEmpty()){
           sb.append(authorFullName.getName());
           sb.append(SEPARATOR);
       }
       if(authorFullName.getSurname() != null && !authorFullName.getSurname().isEmpty()){
           sb.append(authorFullName.getSurname());
           sb.append(SEPARATOR);
       }

       return sb.toString();
    }

    @Override
    public AuthorFullName convertToEntityAttribute(String dbAuthorFullName) {
        if (dbAuthorFullName == null || dbAuthorFullName.isEmpty()) {
            return null;
        }

        String[] pieces = dbAuthorFullName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        AuthorFullName authorFullName = new AuthorFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbAuthorFullName.contains(SEPARATOR)) {
            authorFullName.setName(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                authorFullName.setSurname(pieces[1]);
            }
        } else {
            authorFullName.setName(firstPiece);
        }

        return authorFullName;
    }
}