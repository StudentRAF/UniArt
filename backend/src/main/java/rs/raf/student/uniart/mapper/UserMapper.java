package rs.raf.student.uniart.mapper;

import lombok.experimental.ExtensionMethod;
import rs.raf.student.uniart.dto.user.UserGetDto;
import rs.raf.student.uniart.dto.user.admin.AdminCreateDto;
import rs.raf.student.uniart.dto.user.admin.AdminGetDto;
import rs.raf.student.uniart.dto.user.admin.AdminUpdateDto;
import rs.raf.student.uniart.dto.user.editor.EditorGetDto;
import rs.raf.student.uniart.dto.user.manager.ManagerGetDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.entity.UserRole;
import rs.raf.student.uniart.type.UserRoleType;
import rs.raf.student.uniart.utils.PasswordUtilities;

@ExtensionMethod({UserRoleMapper.class})
public class UserMapper {

    public static User map(User user, AdminUpdateDto updateDto) {
        return user.firstName(updateDto.firstName())
                   .lastName(updateDto.lastName())
                   .username(updateDto.username())
                   .email(updateDto.email())
                   .password(updateDto.password())
                   .dateOfBirth(updateDto.dateOfBirth())
                   .access(updateDto.access());
    }

    public static User map(User user, AdminCreateDto createDto) {
        String salt = PasswordUtilities.generateSalt();

        return user.firstName(createDto.firstName())
                   .lastName(createDto.lastName())
                   .username(createDto.username())
                   .email(createDto.email())
                   .password(PasswordUtilities.hashPassword(createDto.password(), salt))
                   .salt(salt)
                   .dateOfBirth(createDto.dateOfBirth());
    }

    public static User mapEntity(AdminCreateDto createDto) {
        return map(new User(), createDto);
    }

    public static User map(User user, UserRole userRole) {
        user.userRole(userRole);

        return user;
    }

    public static Object mapDto(User user) {
        return mapDto(user, UserRoleType.findRole(user.userRole().name()));
    }

    public static Object mapDto(User user, UserRoleType permission) {
        if (user == null)
            return null;

        return switch (permission) {
            case ADMIN -> new AdminGetDto().firstName(user.firstName())
                                           .lastName(user.lastName())
                                           .username(user.username())
                                           .email(user.email())
                                           .dateOfBirth(user.dateOfBirth())
                                           .userRole(user.userRole().mapDto())
                                           .access(user.access())
                                           .activated(user.activated());

            case MANAGER -> new ManagerGetDto().firstName(user.firstName())
                                               .lastName(user.lastName())
                                               .username(user.username())
                                               .email(user.email())
                                               .dateOfBirth(user.dateOfBirth())
                                               .userRole(user.userRole().mapDto());

            case EDITOR -> new EditorGetDto().firstName(user.firstName())
                                             .lastName(user.lastName())
                                             .username(user.username())
                                             .email(user.email())
                                             .dateOfBirth(user.dateOfBirth())
                                             .userRole(user.userRole().mapDto());

            case VIEWER -> new UserGetDto().firstName(user.firstName())
                                           .lastName(user.lastName())
                                           .username(user.username())
                                           .email(user.email());
        };
    }

}
