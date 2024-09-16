package rs.raf.student.uniart.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.user.manager.ManagerCreateDto;
import rs.raf.student.uniart.dto.user.manager.ManagerUpdateDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.entity.UserRole;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.mapper.UserMapper;
import rs.raf.student.uniart.repository.IUserRepository;
import rs.raf.student.uniart.repository.IUserRoleRepository;

import static rs.raf.student.uniart.exception.ExceptionType.CREATE_USER_NOT_FOUND_USER_ROLE;
import static rs.raf.student.uniart.exception.ExceptionType.UPDATE_USER_NOT_FOUND_USERNAME;

@Service
@RequiredArgsConstructor
@ExtensionMethod({UserMapper.class})
public class ManagerService implements IManagerService {

    private final IUserRepository     repository;
    private final IUserRoleRepository userRoleRepository;

    @Override
    public Object create(ManagerCreateDto createDto) throws UniArtException {
        UserRole userRole = userRoleRepository.findByName(createDto.userRole())
                                              .orElseThrow(() -> new UniArtException(CREATE_USER_NOT_FOUND_USER_ROLE,
                                                                                     createDto.userRole()));

        return repository.save(createDto.mapEntity()
                                        .map(userRole))
                         .mapDto();
    }

    @Override
    public Object update(ManagerUpdateDto updateDto) throws UniArtException {
        User user = repository.findByUsername(updateDto.oldUsername())
                              .orElseThrow(() -> new UniArtException(UPDATE_USER_NOT_FOUND_USERNAME,
                                                                     updateDto.oldUsername()));

        return repository.save(user.map(updateDto))
                         .mapDto();
    }

}
