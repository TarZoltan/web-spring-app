package web.webspringapp.service;

import web.webspringapp.dto.AuthorDTO;
import java.util.List;

public interface AuthorService {

    AuthorDTO addService(AuthorDTO newAuthorDTO);

    List<AuthorDTO> readAllAuthor();

    boolean updateAuthor(Long id, AuthorDTO updatedAuthor);

    boolean deleteAuthor(Long id);

}
