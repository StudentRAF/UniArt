package rs.raf.student.uniart.dto.user_role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class UserRoleDeleteDto {

    @NotBlank
    @Size(max = 32)
    @JsonProperty("name")
    private String name;

    //region Constructors

    public UserRoleDeleteDto() { }

    public UserRoleDeleteDto(String name) {
        setName(name);
    }

    //endregion Constructors

    //region Data

    public UserRoleDeleteDto setName(String name) {
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

        if (object instanceof UserRoleDeleteDto dto)
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
                                    UserRoleDeleteDto.class.getSimpleName(), name);
    }

    //endregion Object

}
