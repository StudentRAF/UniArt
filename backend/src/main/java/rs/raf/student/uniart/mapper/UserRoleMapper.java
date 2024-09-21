package rs.raf.student.uniart.mapper;

import rs.raf.student.uniart.dto.user_role.UserRoleGetDto;
import rs.raf.student.uniart.entity.UserRole;

public class UserRoleMapper {

    public static UserRoleGetDto mapDto(UserRole userRole) {
        return new UserRoleGetDto().setName(userRole.name());
    }

}
