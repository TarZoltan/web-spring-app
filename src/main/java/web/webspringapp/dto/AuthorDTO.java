package web.webspringapp.dto;

import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String nationality;
}
