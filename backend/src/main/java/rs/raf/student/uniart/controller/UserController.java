package rs.raf.student.uniart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.student.uniart.dto.user.UserLoginDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.user.IUserService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.User;

@RestController
@RequiredArgsConstructor
@RequestMapping(User.ROOT)
public class UserController {

    private final IUserService service;

    @GetMapping(User.GET_ALL)
    public ResponseEntity<Page<Object>> getAll(Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK));
    }

    @GetMapping(User.GET_BY_USERNAME)
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findByUsername(username), HttpStatus.OK));
    }

    @PostMapping(User.LOGIN)
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto loginDto) {
        return ExceptionUtils.handleResponse(() -> {
            Object dto   = service.login(loginDto);
            String token = service.authorizationToken();

            return ResponseEntity.ok()
                                 .header(HttpHeaders.AUTHORIZATION, token)
                                 .body(dto);
        });
    }

}
