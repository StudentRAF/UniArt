package rs.raf.student.uniart.mapper;

import lombok.experimental.ExtensionMethod;
import rs.raf.student.uniart.dto.user.UserGetDto;
import rs.raf.student.uniart.dto.user.admin.AdminCreateDto;
import rs.raf.student.uniart.dto.user.admin.AdminGetDto;
import rs.raf.student.uniart.dto.user.admin.AdminUpdateDto;
import rs.raf.student.uniart.dto.user.editor.EditorGetDto;
import rs.raf.student.uniart.dto.user.editor.EditorUpdateDto;
import rs.raf.student.uniart.dto.user.manager.ManagerCreateDto;
import rs.raf.student.uniart.dto.user.manager.ManagerGetDto;
import rs.raf.student.uniart.dto.user.manager.ManagerUpdateDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.entity.UserRole;
import rs.raf.student.uniart.type.UserRoleType;
import rs.raf.student.uniart.utils.PasswordUtilities;

@ExtensionMethod({UserRoleMapper.class})
public class UserMapper {

    public static User map(User user, EditorUpdateDto updateDto) {
        return user.setFirstName(updateDto.firstName())
                   .setLastName(updateDto.lastName())
                   .setUsername(updateDto.username())
                   .setEmail(updateDto.email())
                   .setPassword(PasswordUtilities.hashPassword(updateDto.password(), user.salt()))
                   .setDateOfBirth(updateDto.dateOfBirth());
    }

    public static User map(User user, ManagerUpdateDto updateDto) {
        return user.setFirstName(updateDto.firstName())
                   .setLastName(updateDto.lastName())
                   .setUsername(updateDto.username())
                   .setEmail(updateDto.email())
                   .setPassword(PasswordUtilities.hashPassword(updateDto.password(), user.salt()))
                   .setDateOfBirth(updateDto.dateOfBirth());
    }

    public static User map(User user, ManagerCreateDto createDto) {
        String salt = PasswordUtilities.generateSalt();

        return user.setFirstName(createDto.firstName())
                   .setLastName(createDto.lastName())
                   .setUsername(createDto.username())
                   .setEmail(createDto.email())
                   .setPassword(PasswordUtilities.hashPassword(createDto.password(), salt))
                   .setSalt(salt)
                   .setDateOfBirth(createDto.dateOfBirth());
    }

    public static User mapEntity(ManagerCreateDto createDto) {
        return map(new User(), createDto);
    }

    public static User map(User user, AdminUpdateDto updateDto) {
        return user.setFirstName(updateDto.firstName())
                   .setLastName(updateDto.lastName())
                   .setUsername(updateDto.username())
                   .setEmail(updateDto.email())
                   .setPassword(PasswordUtilities.hashPassword(updateDto.password(), user.salt()))
                   .setDateOfBirth(updateDto.dateOfBirth())
                   .setAccess(updateDto.access());
    }

    public static User map(User user, AdminCreateDto createDto) {
        String salt = PasswordUtilities.generateSalt();

        return user.setFirstName(createDto.firstName())
                   .setLastName(createDto.lastName())
                   .setUsername(createDto.username())
                   .setEmail(createDto.email())
                   .setPassword(PasswordUtilities.hashPassword(createDto.password(), salt))
                   .setSalt(salt)
                   .setDateOfBirth(createDto.dateOfBirth());
    }

    public static User mapEntity(AdminCreateDto createDto) {
        return map(new User(), createDto);
    }

    public static User map(User user, UserRole userRole) {
        user.setUserRole(userRole);

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
