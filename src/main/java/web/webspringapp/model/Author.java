package web.webspringapp.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "Szerző")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "Név")
    private String name;
    @Column (name = "Születési dátum")
    private LocalDate birthDate;
    @Column (name = "Nemzetiség")
    private String nationality;

}
