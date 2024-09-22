package rs.raf.student.uniart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.student.uniart.dto.character.CharacterCreateDto;
import rs.raf.student.uniart.dto.character.CharacterGetDto;
import rs.raf.student.uniart.dto.character.CharacterUpdateDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.character.ICharacterService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.Character;

@RestController
@RequiredArgsConstructor
@RequestMapping(Character.ROOT)
public class CharacterController {

    private final ICharacterService service;

    @GetMapping(Character.GET_ALL_BY_PROJECT)
    public ResponseEntity<Page<CharacterGetDto>> getAllByProject(@PathVariable("project")      String project,
                                                                 @PathVariable("organization") String organization,
                                                                 Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findAll(project, organization, pageable), HttpStatus.OK));
    }

    @GetMapping(Character.GET_ONE)
    public ResponseEntity<CharacterGetDto> getOne(@PathVariable("name")         String character,
                                                  @PathVariable("project")      String project,
                                                  @PathVariable("organization") String organization) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findOne(character, project, organization), HttpStatus.OK));
    }

    @PostMapping(Character.CREATE)
    public ResponseEntity<CharacterGetDto> create(@RequestBody @Valid CharacterCreateDto createDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.create(createDto), HttpStatus.OK));
    }

    @PutMapping(Character.UPDATE)
    public ResponseEntity<CharacterGetDto> update(@RequestBody @Valid CharacterUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(updateDto), HttpStatus.ACCEPTED));
    }

}
