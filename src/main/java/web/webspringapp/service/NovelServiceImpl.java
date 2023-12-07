package web.webspringapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webspringapp.dto.NovelDTO;
import web.webspringapp.model.Novel;
import web.webspringapp.repository.NovelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NovelServiceImpl implements NovelService {

    private final ModelMapper modelMapper;
    private final NovelRepository novelRepository;

    @Autowired
    public NovelServiceImpl(ModelMapper modelMapper, NovelRepository novelRepository) {
        this.modelMapper = modelMapper;
        this.novelRepository = novelRepository;
    }

    @Override
    public NovelDTO addNovel(NovelDTO newNovelDTO) {
        Novel novel = modelMapper.map(newNovelDTO, Novel.class);
        Novel savedNovel = novelRepository.save(novel);
        return modelMapper.map(savedNovel, NovelDTO.class);
    }

    @Override
    public List<NovelDTO> readAllNovels() {
        List<Novel> novels = novelRepository.findAll();

        return novels.stream()
                .map(novel -> modelMapper.map(novel, NovelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateNovel(Long id, NovelDTO updatedNovelDTO) {
        if (!novelRepository.existsById(id)) {
            return false;
        }

        Novel existingNovel = novelRepository.findById(id).orElse(null);
        if (existingNovel != null) {
            existingNovel.setTitle(updatedNovelDTO.getTitle());
            existingNovel.setYear(updatedNovelDTO.getYear());
            existingNovel.setLanguage(updatedNovelDTO.getLanguage());

            novelRepository.save(existingNovel);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteNovel(Long id) {
        if (novelRepository.existsById(id)) {
            novelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}