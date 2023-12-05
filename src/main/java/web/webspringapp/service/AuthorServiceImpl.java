package web.webspringapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webspringapp.dto.AuthorDTO;
import web.webspringapp.model.Author;
import web.webspringapp.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO addService(AuthorDTO newAuthorDTO) {
        Author author = modelMapper.map(newAuthorDTO, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    @Override
    public List<AuthorDTO> readAllAuthor() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateAuthor(Long id, AuthorDTO updatedAuthorDTO) {
        if (!authorRepository.existsById(id)) {
            return false;
        }

        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setName(updatedAuthorDTO.getName());
            existingAuthor.setBirthDate(updatedAuthorDTO.getBirthDate());
            existingAuthor.setNationality(updatedAuthorDTO.getNationality());

            authorRepository.save(existingAuthor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
