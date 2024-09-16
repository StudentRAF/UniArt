package rs.raf.student.uniart.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.user.admin.AdminCreateDto;
import rs.raf.student.uniart.dto.user.admin.AdminUpdateDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.entity.UserRole;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.mapper.UserMapper;
import rs.raf.student.uniart.mapper.UserRoleMapper;
import rs.raf.student.uniart.repository.IUserRepository;
import rs.raf.student.uniart.repository.IUserRoleRepository;

import static rs.raf.student.uniart.exception.ExceptionType.CREATE_USER_NOT_FOUND_USER_ROLE;
import static rs.raf.student.uniart.exception.ExceptionType.UPDATE_USER_NOT_FOUND_USERNAME;
import static rs.raf.student.uniart.exception.ExceptionType.UPDATE_USER_NOT_FOUND_USER_ROLE;

@Service
@RequiredArgsConstructor
@ExtensionMethod({UserRoleMapper.class, UserMapper.class})
public class AdminService implements IAdminService {

    private final IUserRepository     repository;
    private final IUserRoleRepository userRoleRepository;

    @Override
    public Object create(AdminCreateDto createDto) throws UniArtException {
        UserRole userRole = userRoleRepository.findByName(createDto.userRole())
                                              .orElseThrow(() -> new UniArtException(CREATE_USER_NOT_FOUND_USER_ROLE,
                                                                                     createDto.userRole()));

        return repository.save(createDto.mapEntity()
                                        .map(userRole))
                         .mapDto();
    }

    @Override
    public Object update(AdminUpdateDto updateDto) throws UniArtException {
        UserRole userRole = userRoleRepository.findByName(updateDto.userRole())
                                              .orElseThrow(() -> new UniArtException(UPDATE_USER_NOT_FOUND_USER_ROLE,
                                                                                     updateDto.userRole()));
        User user = repository.findByUsername(updateDto.oldUsername())
                              .orElseThrow(() -> new UniArtException(UPDATE_USER_NOT_FOUND_USERNAME,
                                                                     updateDto.oldUsername()));

        return repository.save(user.map(updateDto)
                                   .map(userRole))
                         .mapDto();
    }

}
