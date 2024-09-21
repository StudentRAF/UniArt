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
import rs.raf.student.uniart.dto.organization.OrganizationCreateDto;
import rs.raf.student.uniart.dto.organization.OrganizationGetDto;
import rs.raf.student.uniart.dto.organization.OrganizationUpdateDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.organization.IOrganizationService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.Organization;


@RestController
@RequiredArgsConstructor
@RequestMapping(Organization.ROOT)
public class OrganizationController {

    private final IOrganizationService service;

    @GetMapping(Organization.GET_ALL)
    public ResponseEntity<Page<OrganizationGetDto>> getAll(Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK));
    }

    @GetMapping(Organization.GET_BY_NAME)
    public ResponseEntity<OrganizationGetDto> getByName(@PathVariable("name") String name) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findByName(name), HttpStatus.OK));
    }

    @PostMapping(Organization.REGISTER)
    public ResponseEntity<OrganizationGetDto> register(@RequestBody @Valid OrganizationCreateDto createDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.create(createDto), HttpStatus.OK));
    }

    @PutMapping(Organization.UPDATE)
    public ResponseEntity<OrganizationGetDto> update(@RequestBody @Valid OrganizationUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(updateDto), HttpStatus.ACCEPTED));
    }


}
