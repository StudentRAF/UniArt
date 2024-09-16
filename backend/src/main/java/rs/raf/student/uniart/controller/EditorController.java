package rs.raf.student.uniart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.student.uniart.dto.user.editor.EditorUpdateDto;
import rs.raf.student.uniart.exception.ExceptionUtils;
import rs.raf.student.uniart.service.editor.IEditorService;

import static rs.raf.student.uniart.configuration.Configuration.Controller.Mapping.Editor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Editor.ROOT)
public class EditorController {

    private final IEditorService service;

    @PutMapping(Editor.UPDATE)
    public ResponseEntity<Object> update(@RequestBody @Valid EditorUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(updateDto), HttpStatus.ACCEPTED));
    }

}
