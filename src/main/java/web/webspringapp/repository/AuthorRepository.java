package web.webspringapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.webspringapp.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
