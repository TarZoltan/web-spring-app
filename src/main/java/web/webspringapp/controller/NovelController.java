package web.webspringapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
