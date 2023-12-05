package web.webspringapp.service;

import web.webspringapp.dto.NovelDTO;

import java.util.List;

public interface NovelService {

    NovelDTO addNovel(NovelDTO newNovelDTO);

    List<NovelDTO> readAllNovels();

    boolean updateNovel(Long id, NovelDTO updatedNovel);

    boolean deleteNovel(Long id);
}
