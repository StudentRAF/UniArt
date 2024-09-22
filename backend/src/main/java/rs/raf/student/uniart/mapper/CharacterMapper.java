package rs.raf.student.uniart.mapper;


import lombok.experimental.ExtensionMethod;
import rs.raf.student.uniart.dto.character.CharacterCreateDto;
import rs.raf.student.uniart.dto.character.CharacterGetDto;
import rs.raf.student.uniart.dto.character.CharacterUpdateDto;
import rs.raf.student.uniart.entity.Character;
import rs.raf.student.uniart.entity.Project;
import rs.raf.student.uniart.model.commons.Version;
import rs.raf.student.uniart.model.document.Document;
import rs.raf.student.uniart.model.document.DocumentInfo;

@ExtensionMethod({ProjectMapper.class, OrganizationMapper.class})
public class CharacterMapper {

    public static Character map(Character character, CharacterUpdateDto updateDto) {
        return character.setName(updateDto.oldName())
                        .setDocument(updateDto.document());
    }

    public static Character mapEntity(CharacterUpdateDto updateDto) {
        return map(new Character(), updateDto);
    }

    public static Character map(Character character, CharacterCreateDto createDto) {
        return character.setName(createDto.name())
                        .setDocument(new Document().setInfo(new DocumentInfo("uniart", createDto.name(), new Version(1, 0, 0))));
    }

    public static Character mapEntity(CharacterCreateDto createDto, Character character, Project project) {
        return map(mapEntity(createDto), project);
    }

    public static Character mapEntity(CharacterCreateDto createDto) {
        return map(new Character(), createDto);
    }

    public static Character map(Character character, Project project) {
        return character.setProject(project);
    }

    public static CharacterGetDto mapDto(Character character) {
        return new CharacterGetDto().setName(character.name())
                                    .setProject(character.project()
                                                         .mapDto())
                                    .setOrganization(character.project()
                                                              .organization()
                                                              .mapDto())
                                    .setDocument(character.document());
    }

}
