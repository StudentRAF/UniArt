package rs.raf.student.uniart.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleType {

    ADMIN  ("Admin"  ),
    MANAGER("Manager"),
    EDITOR ("Editor" ),
    VIEWER ("Viewer" );

    private static final String rolePrefix = "ROLE_";

    private final String name;

    public static UserRoleType findRole(String name) {
        for (UserRoleType role : UserRoleType.values())
            if (role.name.equals(name))
                return role;

        return VIEWER;
    }

    public String securityName() {
        return rolePrefix + name;
    }

    @Override
    public String toString() {
        return name;
    }

}
