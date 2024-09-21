package rs.raf.student.uniart.dto.user_role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class UserRoleUpdateDto {

    @NotBlank
    @Size(max = 32)
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(max = 32)
    @JsonProperty("old_name")
    private String oldName;

    //region Constructors

    public UserRoleUpdateDto() { }

    public UserRoleUpdateDto(String name, String oldName) {
        setName(name);
        setOldName(oldName);
    }

    //endregion Constructors

    //region Data

    public UserRoleUpdateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public UserRoleUpdateDto setOldName(String oldName) {
        this.oldName = oldName;

        return this;
    }

    public String oldName() {
        return oldName;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserRoleUpdateDto dto)
            return Objects.equals(dto.name, name) &&
                   Objects.equals(dto.oldName, oldName);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, oldName);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} | oldName = {2} '}'\
                                    """,
                                    UserRoleUpdateDto.class.getSimpleName(), name, oldName);
    }

    //endregion Object

}
