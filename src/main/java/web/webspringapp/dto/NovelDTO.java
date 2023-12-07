package web.webspringapp.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelDTO {
    private Long id;
    private String title;
    private int year;
    private String language;
    private AuthorDTO author;
}
