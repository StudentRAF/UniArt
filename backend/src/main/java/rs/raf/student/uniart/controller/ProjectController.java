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
import rs.raf.student.uniart.dto.project.ProjectCreateDto;
import rs.raf.student.uniart.dto.project.ProjectGetDto;
import rs.raf.student.uniart.dto.project.ProjectUpdateDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.project.IProjectService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.Project;

@RestController
@RequiredArgsConstructor
@RequestMapping(Project.ROOT)
public class ProjectController {

    private final IProjectService service;

    @GetMapping(Project.GET_ALL)
    public ResponseEntity<Page<ProjectGetDto>> getAll(Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK));
    }

    @GetMapping(Project.GET_ALL_BY_ORGANIZATION)
    public ResponseEntity<Page<ProjectGetDto>> getAllByOrganization(@PathVariable("organization") String organization,
                                                                    Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findByOrganization(organization, pageable), HttpStatus.OK));
    }

    @GetMapping(Project.GET_BY_NAME)
    public ResponseEntity<ProjectGetDto> getByName(@PathVariable("name") String name,
                                                   @PathVariable("organization") String organization) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findByName(name, organization), HttpStatus.OK));
    }

    @PostMapping(Project.REGISTER)
    public ResponseEntity<ProjectGetDto> register(@RequestBody @Valid ProjectCreateDto createDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.create(createDto), HttpStatus.OK));
    }

    @PutMapping(Project.UPDATE)
    public ResponseEntity<ProjectGetDto> update(@RequestBody @Valid ProjectUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(updateDto), HttpStatus.ACCEPTED));
    }

}
