package rs.raf.student.uniart.extension;

import lombok.experimental.ExtensionMethod;
import org.springframework.security.core.Authentication;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.mapper.UserMapper;
import rs.raf.student.uniart.type.UserRoleType;

import java.util.Objects;

@ExtensionMethod({UserMapper.class})
public class AuthenticationExtension {

    public static UserRoleType permissionType(Authentication authentication, User user) {
        if (authentication.getPrincipal() instanceof User authenticatedUser) {
            if (Objects.equals(user, authenticatedUser))
                return UserRoleType.findRole(user.userRole().name());

            return switch (UserRoleType.findRole(authenticatedUser.userRole().name())) {
                case ADMIN                   -> UserRoleType.ADMIN;
                case MANAGER, EDITOR, VIEWER -> UserRoleType.VIEWER;
            };
        }

        return UserRoleType.VIEWER;
    }

}
