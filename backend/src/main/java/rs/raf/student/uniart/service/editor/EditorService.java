package rs.raf.student.uniart.service.editor;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.user.editor.EditorUpdateDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.mapper.UserMapper;
import rs.raf.student.uniart.repository.IUserRepository;

import static rs.raf.student.uniart.exception.ExceptionType.UPDATE_USER_NOT_FOUND_USERNAME;

@Service
@RequiredArgsConstructor
@ExtensionMethod({UserMapper.class})
public class EditorService implements IEditorService {

    private final IUserRepository repository;

    @Override
    public Object update(EditorUpdateDto updateDto) throws UniArtException {
        User user = repository.findByUsername(updateDto.oldUsername())
                              .orElseThrow(() -> new UniArtException(UPDATE_USER_NOT_FOUND_USERNAME,
                                                                     updateDto.oldUsername()));

        return repository.save(user.map(updateDto))
                         .mapDto();
    }

}
