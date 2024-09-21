package rs.raf.student.uniart.mapper;

import lombok.experimental.ExtensionMethod;
import rs.raf.student.uniart.dto.project.ProjectCreateDto;
import rs.raf.student.uniart.dto.project.ProjectGetDto;
import rs.raf.student.uniart.dto.project.ProjectUpdateDto;
import rs.raf.student.uniart.entity.Organization;
import rs.raf.student.uniart.entity.Project;

@ExtensionMethod({OrganizationMapper.class})
public class ProjectMapper {

    public static Project map(Project project, ProjectUpdateDto updateDto) {
        return project.setName(updateDto.name());
    }

    public static Project mapEntity(ProjectUpdateDto updateDto) {
        return map(new Project(), updateDto);
    }

    public static Project map(Project project, ProjectCreateDto createDto) {
        return project.setName(createDto.name());
    }

    public static Project mapEntity(ProjectCreateDto createDto, Organization organization) {
        return map(mapEntity(createDto), organization);
    }

    public static Project mapEntity(ProjectCreateDto createDto) {
        return map(new Project(), createDto);
    }

    public static Project map(Project project, Organization organization) {
        project.setOrganization(organization);

        return project;
    }

    public static ProjectGetDto mapDto(Project project) {
        return new ProjectGetDto().setName(project.name())
                                  .setOrganization(project.organization()
                                                          .mapDto());
    }

}
