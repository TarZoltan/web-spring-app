package web.webspringapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.webspringapp.dto.NovelDTO;
import web.webspringapp.model.Novel;
import web.webspringapp.repository.NovelRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NovelController {

    private final ModelMapper modelMapper;
    private final NovelRepository novelRepository;

    @Autowired
    public NovelController(ModelMapper modelMapper, NovelRepository novelRepository) {
        this.modelMapper = modelMapper;
        this.novelRepository = novelRepository;
    }

    @PostMapping("/addnovel")
    public NovelDTO addNovel(@RequestBody NovelDTO novelDTO) {
        Novel newNovel = modelMapper.map(novelDTO, Novel.class);
        Novel savedNovel = novelRepository.save(newNovel);
        return modelMapper.map(savedNovel, NovelDTO.class);
    }

    @GetMapping("/novels")
    public ResponseEntity<List<NovelDTO>> getAllNovels() {
        List<Novel> novels = novelRepository.findAll();

        if (novels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<NovelDTO> novelDTOs = novels.stream()
                .map(novel -> modelMapper.map(novel, NovelDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(novelDTOs);
    }

    @PutMapping("/updatenovel/{id}")
    public ResponseEntity<NovelDTO> updateNovel(
            @PathVariable Long id,
            @RequestBody NovelDTO updatedNovelDTO) {

        if (!novelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Novel existingNovel = novelRepository.findById(id).orElse(null);
        if (existingNovel != null) {
            existingNovel.setYear(updatedNovelDTO.getYear());
            existingNovel.setLanguage(updatedNovelDTO.getLanguage());
            existingNovel.setTitle(updatedNovelDTO.getTitle());

            Novel updatedNovel = novelRepository.save(existingNovel);

            NovelDTO responseNovelDTO = modelMapper.map(updatedNovel, NovelDTO.class);
            return ResponseEntity.ok(responseNovelDTO);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
