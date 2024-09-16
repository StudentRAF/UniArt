package rs.raf.student.uniart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.student.uniart.dto.user.admin.AdminCreateDto;
import rs.raf.student.uniart.dto.user.admin.AdminUpdateDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.admin.IAdminService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.Admin;

@RestController
@RequiredArgsConstructor
@RequestMapping(Admin.ROOT)
public class AdminController {

    private final IAdminService service;

    @PostMapping(Admin.REGISTER)
    public ResponseEntity<Object> register(@RequestBody @Valid AdminCreateDto createDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.create(createDto), HttpStatus.OK));
    }

    @PutMapping(Admin.UPDATE)
    public ResponseEntity<Object> update(@RequestBody @Valid AdminUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(updateDto), HttpStatus.ACCEPTED));
    }

}
