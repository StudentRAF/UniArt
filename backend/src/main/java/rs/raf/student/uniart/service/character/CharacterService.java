package rs.raf.student.uniart.service.character;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.character.CharacterCreateDto;
import rs.raf.student.uniart.dto.character.CharacterGetDto;
import rs.raf.student.uniart.dto.character.CharacterUpdateDto;
import rs.raf.student.uniart.entity.Character;
import rs.raf.student.uniart.entity.Project;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.mapper.CharacterMapper;
import rs.raf.student.uniart.repository.ICharacterRepository;
import rs.raf.student.uniart.repository.IProjectRepository;
import rs.raf.student.uniart.specification.CharacterSpecification;

@Service
@RequiredArgsConstructor
@ExtensionMethod({CharacterMapper.class})
public class CharacterService implements ICharacterService {

    private final ICharacterRepository repository;
    private final IProjectRepository   projectRepository;

    @Override
    public Page<CharacterGetDto> findAll(String project, String organization, Pageable pageable) throws UniArtException {
        return repository.findAll(CharacterSpecification.FindAll.of(project, organization),
                                  pageable)
                         .map(character -> character.mapDto());
    }

    @Override
    public CharacterGetDto findOne(String character, String project, String organization) throws UniArtException {
        return repository.findOne(CharacterSpecification.Find.of(character, project, organization))
                         .orElseThrow()
                         .mapDto();
    }

    @Override
    public CharacterGetDto create(CharacterCreateDto createDto) throws UniArtException {
        Project project = projectRepository.findByNameAndOrganizationName(createDto.project(), createDto.organization())
                                           .orElseThrow();

        return repository.save(createDto.mapEntity()
                                        .map(project))
                         .mapDto();
    }

    @Override
    public CharacterGetDto update(CharacterUpdateDto updateDto) throws UniArtException {
        Character character = repository.findOne(CharacterSpecification.Find.of(updateDto.oldName(), updateDto.project(), updateDto.organization()))
                                        .orElseThrow();

        return repository.save(character.map(updateDto))
                         .mapDto();
    }

}
