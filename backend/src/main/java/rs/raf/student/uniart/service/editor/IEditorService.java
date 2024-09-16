package rs.raf.student.uniart.service.editor;

import rs.raf.student.uniart.dto.user.editor.EditorUpdateDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface IEditorService {

    Object update(EditorUpdateDto updateDto) throws UniArtException;

}
