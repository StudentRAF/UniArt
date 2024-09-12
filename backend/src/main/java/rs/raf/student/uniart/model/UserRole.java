package rs.raf.student.uniart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN  ("Admin"  ),
    MANAGER("Manager"),
    EDITOR ("Editor" ),;

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    public static UserRole findRole(String name) {
        for (UserRole role : UserRole.values())
            if (role.name.equals(name))
                return role;

        return null;
    }

}
