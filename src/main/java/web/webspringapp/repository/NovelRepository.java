package web.webspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.webspringapp.model.Author;
import web.webspringapp.model.Novel;
public interface NovelRepository  extends JpaRepository<Novel, Integer>{
}
