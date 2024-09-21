package rs.raf.student.uniart.dto.user_role;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.Objects;

public class UserRoleGetDto {

    @JsonProperty("name")
    private String name;

    //region Constructors

    public UserRoleGetDto() { }

    public UserRoleGetDto(String name) {
        setName(name);
    }

    //endregion Constructors

    //region Data

    public UserRoleGetDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserRoleGetDto dto)
            return Objects.equals(dto.name, name);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} '}'\
                                    """,
                                    UserRoleGetDto.class.getSimpleName(), name);
    }

    //endregion Object

}
