package web.webspringapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.webspringapp.model.Author;
import web.webspringapp.dto.AuthorDTO;
import web.webspringapp.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/addauthor")
    public AuthorDTO addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AuthorDTO> authorDTOs = authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(authorDTOs);
    }

    @PutMapping("/updateauthor/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDTO updatedAuthorDTO) {


        if (!authorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }


        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setName(updatedAuthorDTO.getName());
            existingAuthor.setBirthDate(updatedAuthorDTO.getBirthDate());
            existingAuthor.setNationality(updatedAuthorDTO.getNationality());


            Author updatedAuthor = authorRepository.save(existingAuthor);


            AuthorDTO responseAuthorDTO = modelMapper.map(updatedAuthor, AuthorDTO.class);
            return ResponseEntity.ok(responseAuthorDTO);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

}