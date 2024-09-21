package rs.raf.student.uniart.service.project;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.project.ProjectCreateDto;
import rs.raf.student.uniart.dto.project.ProjectGetDto;
import rs.raf.student.uniart.dto.project.ProjectUpdateDto;
import rs.raf.student.uniart.entity.Organization;
import rs.raf.student.uniart.entity.Project;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.mapper.ProjectMapper;
import rs.raf.student.uniart.repository.IOrganizationRepository;
import rs.raf.student.uniart.repository.IProjectRepository;

import static rs.raf.student.uniart.exception.ExceptionType.CREATE_PROJECT_NOT_FOUND_ORGANIZATION;
import static rs.raf.student.uniart.exception.ExceptionType.FIND_PROJECT_NOT_FOUND_NAME_OR_ORGANIZATION;
import static rs.raf.student.uniart.exception.ExceptionType.UPDATE_PROJECT_NOT_FOUND_NAME_OR_ORGANIZATION;

@Service
@RequiredArgsConstructor
@ExtensionMethod({ProjectMapper.class})
public class ProjectService implements IProjectService {

    private final IProjectRepository      repository;
    private final IOrganizationRepository organizationRepository;

    @Override
    public Page<ProjectGetDto> findAll(Pageable pageable) throws UniArtException {
        return repository.findAll(pageable)
                         .map(project -> project.mapDto());
    }

    @Override
    public Page<ProjectGetDto> findByOrganization(String organization, Pageable pageable) throws UniArtException {
        return repository.findAllByOrganizationName(organization, pageable)
                         .map(project -> project.mapDto());
    }

    @Override
    public ProjectGetDto findByName(String name, String organization) throws UniArtException {
        return repository.findByNameAndOrganizationName(name, organization)
                         .orElseThrow(() -> new UniArtException(FIND_PROJECT_NOT_FOUND_NAME_OR_ORGANIZATION,
                                                                name))
                         .mapDto();
    }

    @Override
    public ProjectGetDto create(ProjectCreateDto createDto) throws UniArtException {
        Organization organization = organizationRepository.findByName(createDto.organization())
                                                          .orElseThrow(() -> new UniArtException(CREATE_PROJECT_NOT_FOUND_ORGANIZATION,
                                                                                                 createDto.organization()));

        return repository.save(createDto.mapEntity()
                                        .map(organization))
                         .mapDto();
    }

    @Override
    public ProjectGetDto update(ProjectUpdateDto updateDto) throws UniArtException {
        Project project = repository.findByNameAndOrganizationName(updateDto.oldName(), updateDto.organization())
                                    .orElseThrow(() -> new UniArtException(UPDATE_PROJECT_NOT_FOUND_NAME_OR_ORGANIZATION,
                                                                           updateDto.oldName(),
                                                                           updateDto.organization()));

        return repository.save(project.map(updateDto))
                         .mapDto();
    }

}
