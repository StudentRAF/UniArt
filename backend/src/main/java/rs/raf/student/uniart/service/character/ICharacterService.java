package rs.raf.student.uniart.service.character;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.student.uniart.dto.character.CharacterCreateDto;
import rs.raf.student.uniart.dto.character.CharacterGetDto;
import rs.raf.student.uniart.dto.character.CharacterUpdateDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface ICharacterService {

    Page<CharacterGetDto> findAll(String project, String organization, Pageable pageable) throws UniArtException;

    CharacterGetDto findOne(String character, String project, String organization) throws UniArtException;

    CharacterGetDto create(CharacterCreateDto createDto) throws UniArtException;

    CharacterGetDto update(CharacterUpdateDto updateDto) throws UniArtException;

}
