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
@Table (name = "Regény")
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "Cím")
    private String title;

    @Column (name = "Megjelenés")
    private int published;
    @Column (name = "Nyelv")
    private String language;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Szerző_ID")
    private Author author;
}
