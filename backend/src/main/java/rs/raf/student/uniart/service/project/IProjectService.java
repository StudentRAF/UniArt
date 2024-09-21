package rs.raf.student.uniart.service.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.student.uniart.dto.project.ProjectCreateDto;
import rs.raf.student.uniart.dto.project.ProjectGetDto;
import rs.raf.student.uniart.dto.project.ProjectUpdateDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface IProjectService {

    Page<ProjectGetDto> findAll(Pageable pageable) throws UniArtException;

    Page<ProjectGetDto> findByOrganization(String organization, Pageable pageable) throws UniArtException;

    ProjectGetDto findByName(String name, String organisation) throws UniArtException;

    ProjectGetDto create(ProjectCreateDto createDto) throws UniArtException;

    ProjectGetDto update(ProjectUpdateDto updateDto) throws UniArtException;

}
