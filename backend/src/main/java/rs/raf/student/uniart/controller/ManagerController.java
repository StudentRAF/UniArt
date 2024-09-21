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
import rs.raf.student.uniart.dto.user.manager.ManagerCreateDto;
import rs.raf.student.uniart.dto.user.manager.ManagerUpdateDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.manager.IManagerService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.Manager;

@RestController
@RequiredArgsConstructor
@RequestMapping(Manager.ROOT)
public class ManagerController {

    private final IManagerService service;

    @PostMapping(Manager.CREATE)
    public ResponseEntity<Object> create(@RequestBody @Valid ManagerCreateDto createDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.create(createDto), HttpStatus.OK));
    }

    @PutMapping(Manager.UPDATE)
    public ResponseEntity<Object> update(@RequestBody @Valid ManagerUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(updateDto), HttpStatus.ACCEPTED));
    }

}
