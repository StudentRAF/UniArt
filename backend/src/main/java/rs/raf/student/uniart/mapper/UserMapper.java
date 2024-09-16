package rs.raf.student.uniart.mapper;

import lombok.experimental.ExtensionMethod;
import rs.raf.student.uniart.dto.user.UserGetDto;
import rs.raf.student.uniart.dto.user.admin.AdminGetDto;
import rs.raf.student.uniart.dto.user.editor.EditorGetDto;
import rs.raf.student.uniart.dto.user.manager.ManagerGetDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.type.UserRoleType;

@ExtensionMethod({UserRoleMapper.class})
public class UserMapper {

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
